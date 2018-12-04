package soundDetector;


import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.audio.AudioPlayer;
import org.openimaj.audio.SampleChunk;
import org.openimaj.audio.analysis.FourierTransform;
import org.openimaj.audio.features.MFCC;
import org.openimaj.audio.samples.SampleBuffer;
import org.openimaj.video.xuggle.XuggleAudio;
import org.openimaj.vis.audio.AudioWaveform;
import soundDetector.clustering.Cluster;
import soundDetector.clustering.ClusterManager;
import soundDetector.data.Model;
import soundDetector.descriptor.Descriptor;
import soundDetector.descriptor.DescriptorCalculator;
import soundDetector.file.FileManager;
import soundDetector.song.Song;
import soundDetector.song.SongManager;
import soundDetector.song.SongPart;


/**
 * OpenIMAJ Hello world!
 *
 */
public class App {
    /**
     * @trimDuration dlzka skratenie skladby
     * @descriptorsPerSong pocet usekov, pre ktore sa pocitaju descriptory
     */
    private static final int trimDuration = 5;
    private static final int descriptorsPerSong = 5;
    private static Model model;
    private static FileManager fileManager;
    private static SongManager songManager;
    private static ClusterManager clusterManager;
    private static DescriptorCalculator des;
    public static void main(String[] args) {
        //first need to inicialize objects
        model = new Model();
        fileManager = new FileManager(model);
        clusterManager = new ClusterManager(model);
        songManager = new SongManager(model);
        des = new DescriptorCalculator(model);
        
        
        
        //upload all cluster songs to model
        uploadClusterSongs();
        
        //Jedna varianta je urobit uploadClusterSongs() a nasledne si vysledok ulozit cez fileManager.exportToFile(Path to file)
        //Druha varianta je nacitanie descriptorov cez fileManager.importFromFile(Path to file) 
        
        fileManager.exportToFile("C:\\Own_Projects\\MI-VMW\\tests\\export.json");
        //fileManager.importFromFile("C:\\Own_Projects\\MI-VMW\\tests\\export.json");
        uploadWorkingSong("C:\\Own_Projects\\MI-VMW\\tests\\Sunshine Original Soundtrack - Surface of the Sun (Early promo version) [HD].mp4.mp3");
        System.out.println("OneToMany:");
        
        //pocitanie 1:n s vahami na descriptoroch
        clusterManager.computeClusteringResultOneToMany().printResults();
        System.out.println("");
        System.out.println("");
        System.out.println("OneToOne:");
        
        //pocitanie 1:1 s vahami na descriptoroch
        clusterManager.computeClusteringResultOneToOne().printResults();
        
    }
    
    /**
     * @path path to the song which need to be analyzed
    */
    private static void uploadWorkingSong(String path){
        songManager.uploadComputingSong(new File(path));
        songManager.trimComputingSong(trimDuration);
        des.computeDescriptors(model.getComputingSong(), descriptorsPerSong);
    }
    
    /**
     * upload all clusters songs from different genres, trim them and compute Descriptors
     */
    private static void uploadClusterSongs(){
        //ExecutorService executor = Executors.newFixedThreadPool(8);
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(16, 16, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(getNumSongs()+1));
        
        final CountDownLatch latch = new CountDownLatch(getNumSongs());
        File[] genres = new File("../genres/").listFiles();
        for (File genre : genres) {
            File[] songs = genre.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".mp3");
                }
            });
            if(!model.getClusters().containsKey(genre.getName())){
                model.getClusters().put(genre.getName(), new Cluster(genre.getName()));
            }
            for (File song : songs) {
                System.out.println("Song name: "+song.getName()+", song genre: "+song.getParentFile().getName());
                final File s = song;
                final File g = genre;
                executorPool.submit(new Runnable(){ public void run() { 
  Song songTmp = new Song(s);
                songManager.trimSong(songTmp, trimDuration);
                des.computeDescriptors(songTmp, descriptorsPerSong);
                songTmp.setAudio(null);
                model.getClusters().get(g.getName()).getSongs().add(songTmp); 
                latch.countDown();
                    System.out.println("latch:"+latch.getCount());
}});            
            }
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        executorPool.shutdown();
    }

    private static int getNumSongs() {
        int n = 0;
        File[] genres = new File("../genres/").listFiles();
        for (File genre : genres) {
            File[] songs = genre.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".mp3");
                }
            });
            n+=songs.length;
        }
        return n;
    }
}
