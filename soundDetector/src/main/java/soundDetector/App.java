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
    public static void main(String[] args) {
        //first need to inicialize model
        Model model = new Model();
        ClusterManager clusterManager = new ClusterManager(model);
        //create songManager instance
        SongManager songManager = new SongManager(model);
        //create instance for mass descriptor calculator
        DescriptorCalculator des = new DescriptorCalculator(model);
        //upload all songs to model
        int uploadedSongs = songManager.uploadSongs();
        System.out.println("Uploaded songs: "+uploadedSongs);
        //trim all songs into 3 seconds intervals 
        songManager.trimSongs(trimDuration);
        //compute descriptors for 5 SongParts for every song (rovnomerne rozdelene)
        des.computeDescriptors(descriptorsPerSong);
        songManager.uploadComputingSong(new File("C:\\Own_Projects\\MI-VMW\\tests\\Hans Zimmer - Epilogue Main Theme.mp3"));
        songManager.trimComputingSong(trimDuration);
        des.computeDescriptors(model.getComputingSong(), descriptorsPerSong);
        clusterManager.computeClusteringResult().printResults();
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
}
