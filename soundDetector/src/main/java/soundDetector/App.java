package soundDetector;


import java.io.File;
import org.openimaj.audio.AudioPlayer;
import org.openimaj.audio.SampleChunk;
import org.openimaj.audio.analysis.FourierTransform;
import org.openimaj.audio.features.MFCC;
import org.openimaj.audio.samples.SampleBuffer;
import org.openimaj.video.xuggle.XuggleAudio;
import org.openimaj.vis.audio.AudioWaveform;
import soundDetector.descriptor.Descriptor;
import soundDetector.descriptor.DescriptorCalculator;


/**
 * OpenIMAJ Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        XuggleAudio xa0 = new XuggleAudio(new File("../genres/soundtrack/Future World Music - Dream Chasers.mp3"));
        XuggleAudio xa1 = new XuggleAudio(new File("../genres/techno/Club Inferno - scarf.mp3"));
        //XuggleAudio xa = new XuggleAudio(new File("../genres/techno/Eiffel 65 - Blue (Da Ba Dee)-[AudioTrimmer.com].mp3"));
        DescriptorCalculator calculator = new DescriptorCalculator();
        Descriptor soundtrack = calculator.computeDescriptor(xa0);
        Descriptor techno = calculator.computeDescriptor(xa1);
        MFCC mfcc = new MFCC(xa1);
        SampleChunk sc = null;
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
        soundtrack.print();
        techno.print();
    }
}
