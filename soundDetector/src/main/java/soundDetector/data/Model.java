/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.data;

import java.util.HashMap;
import soundDetector.clustering.Cluster;
import soundDetector.song.Song;

/**
 *
 * @author Brka
 */
public class Model {
    private HashMap<String,Cluster> clusters;
    private Song computingSong;
    public Model(){
        this.clusters = new HashMap<String,Cluster>();
    }

    public HashMap<String,Cluster> getClusters() {
        return clusters;
    }

    public Song getComputingSong() {
        return computingSong;
    }

    public void setComputingSong(Song computingSong) {
        this.computingSong = computingSong;
    }
    
    
    
    
    
}
