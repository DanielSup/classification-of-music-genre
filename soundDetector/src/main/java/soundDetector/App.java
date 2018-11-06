package soundDetector;


import java.io.File;
import org.openimaj.audio.AudioPlayer;
import org.openimaj.audio.SampleChunk;
import org.openimaj.audio.analysis.FourierTransform;
import org.openimaj.audio.features.MFCC;
import org.openimaj.video.xuggle.XuggleAudio;
import org.openimaj.vis.audio.AudioWaveform;


/**
 * OpenIMAJ Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        XuggleAudio xa0 = new XuggleAudio(new File("../genres/techno/Crazy Frog - Axel F-[AudioTrimmer.com].mp3"));
        XuggleAudio xa = new XuggleAudio(new File("../genres/techno/Eiffel 65 - Blue (Da Ba Dee)-[AudioTrimmer.com].mp3"));
        SampleChunk sc = null;
        /** while ((sc = xa.nextSampleChunk()) != null) {
            
            byte[] samples = sc.getSamples();
            for(int i = 0; i < samples.length; i++) {
                // System.out.println(samples[i]);
            }
           // System.out.println("");
        }
        System.out.println(""); **/
        long time = System.currentTimeMillis();
        MFCC mfcc = new MFCC(xa);
        
        while ((sc = mfcc.nextSampleChunk()) != null) {
            double[][] mfccs = mfcc.getLastCalculatedFeature();
            for (int i = 0; i < mfccs.length; i++) {
                for (int j = 0; j < mfccs[i].length; j++) {
                    System.out.print(mfccs[i][j]+" ");
                }
                System.out.println("");
            }
        }
        System.out.println("");
        
        MFCC mfcc0 = new MFCC(xa0);
        
        while ((sc = mfcc0.nextSampleChunk()) != null) {
            double[][] mfccs = mfcc0.getLastCalculatedFeature();
            for (int i = 0; i < mfccs.length; i++) {
                for (int j = 0; j < mfccs[i].length; j++) {
                    System.out.print(mfccs[i][j]+" ");
                }
                System.out.println("");
            }
        }
        long current = System.currentTimeMillis();
        System.out.println(current-time+" ms");
        System.out.println("");
    }
}
