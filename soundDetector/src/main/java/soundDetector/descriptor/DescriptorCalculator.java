/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.descriptor;

import org.openimaj.audio.SampleChunk;
import org.openimaj.audio.features.MFCC;
import org.openimaj.video.xuggle.XuggleAudio;
import soundDetector.data.Model;
import soundDetector.song.Song;
import soundDetector.song.SongPart;

/**
 *
 * @author Brka
 * Class for cumputing simple Descriptor
 */
public class DescriptorCalculator {
    private Model model;
    public DescriptorCalculator(Model model) {
        this.model = model;
    }
    /**
     * 
     * @param audio the input object for which the "Descriptor" will be computed
     * @return Descriptor object with computed values
     */
    public void computeDescriptors(int countPerSong){
        for(Song song : model.getSongs().values()){
            for(int i = 0; i < countPerSong; i++){
                int partsCount = song.getSongParts().size();
                song.getSongParts().get(i*(partsCount/countPerSong)).computeDescriptor();
            }
        }
       
    }
}
