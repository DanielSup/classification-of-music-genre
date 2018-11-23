package soundDetector;


import java.io.File;
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
    private static final int trimDuration = 2;
    private static final int descriptorsPerSong = 20;
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
        //uploadClusterSongs();
        //fileManager.exportToFile("C:\\Own_Projects\\MI-VMW\\tests\\export.json");
        fileManager.importFromFile("C:\\Own_Projects\\MI-VMW\\tests\\export.json");
        uploadWorkingSong("C:\\Own_Projects\\MI-VMW\\tests\\John Denver - Take Me Home Country Roads (Audio).mp3");
        System.out.println("OneToMany:");
        clusterManager.computeClusteringResultOneToMany().printResults();
        System.out.println("");
        System.out.println("");
        System.out.println("OneToOne:");
        clusterManager.computeClusteringResultOneToOne().printResults();
        
        
        //for(Cluster cluster : model.getClusters().values()){
        //    cluster.computeDistanceBetweenClusterSongs();
        //}
        //show the computed descriptors for each song
        /*for(Song song : model.getSongs().values()){
            System.out.println("Song: "+song.getName()+", genre: "+song.getGenre()+", descriptors: ");
            for(SongPart songPart : song.getSongParts()){
                if(songPart.getDescriptor()!=null){
                    songPart.getDescriptor().print();
                }
            }
        }*/
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
        songManager.uploadSongs();
        songManager.trimSongs(trimDuration);
        des.computeDescriptors(descriptorsPerSong);
    }
}
