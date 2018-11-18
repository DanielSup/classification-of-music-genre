/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.data;

import java.util.HashMap;
import soundDetector.song.Song;

/**
 *
 * @author Brka
 */
public class Model {
    private HashMap<String,Song> songs;
    
    public Model(){
        this.songs = new HashMap<String,Song>();
    }

    public HashMap<String,Song> getSongs() {
        return songs;
    }
    
}
