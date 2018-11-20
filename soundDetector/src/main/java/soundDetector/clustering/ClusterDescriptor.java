/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.clustering;

import soundDetector.song.Song;

/**
 *
 * @author Brka
 */
public class ClusterDescriptor {
    private Song song;
    private String descriptor;
    private int descriptorID;
    private double value;
    public ClusterDescriptor(Song song, String descriptor, int descriptorID, double value){
        this.song = song;
        this.descriptor = descriptor;
        this.descriptorID = descriptorID;
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSong(Song song){
        this.song = song;
    }
    public Song getSong() {
        return song;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public int getDescriptorID() {
        return descriptorID;
    }

    public double getValue() {
        return value;
    }
    
    
}
