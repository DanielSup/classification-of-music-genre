/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.song;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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
    private final Comparator comp = new Comparator<SongPart>() {
    @Override
    public int compare(SongPart o1, SongPart o2) {
        if(o1.getStartTime()<o2.getStartTime()){
            return -1;
        }else if(o1.getStartTime()==o2.getStartTime()){
            return 0;
        }else{
            return 1;
        }
    }
};

    public void setAudio(XuggleAudio audio) {
        this.audio = audio;
    }
    
    public Song(File file){
        audio = new XuggleAudio(file);
        genre = file.getParentFile().getName();
        name = file.getName();
        songParts = new ArrayList<SongPart>();
        songPartsComputedDescriptor = new ArrayList<SongPart>();
    }
    
    public Song(String name, String genre){
        this.name = name;
        this.genre = genre;
        songParts = new ArrayList<SongPart>();
        songPartsComputedDescriptor = new ArrayList<SongPart>();
    }


    public void sortAllSongParts(){
        songParts.sort(comp); 
        songPartsComputedDescriptor.sort(comp);
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
