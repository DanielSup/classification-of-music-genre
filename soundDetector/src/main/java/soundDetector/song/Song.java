/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.song;

import java.io.File;
import java.util.ArrayList;
import org.openimaj.video.xuggle.XuggleAudio;

/**
 * 
 * @author Brka
 */
public class Song {
    /**
     * @songParts parts of the song
     * 
     */
    private ArrayList<SongPart> songParts;
    private ArrayList<SongPart> songPartsComputedDescriptor;
    private String genre;
    private String name;
    private XuggleAudio audio;
    
    public Song(File file){
        audio = new XuggleAudio(file);
        genre = file.getParentFile().getName();
        name = file.getName();
        songParts = new ArrayList<SongPart>();
        songPartsComputedDescriptor = new ArrayList<SongPart>();
    }


    
    public ArrayList<SongPart> getSongParts() {
        return songParts;
    }

    public ArrayList<SongPart> getSongPartsComputedDescriptor() {
        return songPartsComputedDescriptor;
    }
    
    

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public XuggleAudio getAudio() {
        return audio;
    }
    
    
}
