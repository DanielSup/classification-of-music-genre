/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import soundDetector.data.Model;
import soundDetector.song.Song;
import soundDetector.song.SongPart;

/**
 *
 * @author Brka
 */
public class Cluster {
    private String genre;
    private ArrayList<Song> songs;
    private HashMap<Integer,ClusterFactorDescriptor> factor;
    public Cluster(String genre){
        this.genre = genre;
        this.songs = new ArrayList<Song>();
        this.factor = new HashMap<Integer,ClusterFactorDescriptor>();
    }

    public HashMap<Integer,ClusterFactorDescriptor> getFactor() {
        return factor;
    }
    

    
    public String getGenre() {
        return genre;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    public void computeDistanceBetweenClusterSongs(){
        System.out.println("Cluster : "+genre+", distances:");
        for(int i = 0; i < songs.get(0).getSongPartsComputedDescriptor().size(); i++){
            
            boolean first;
            
                for(int n=0;n<13;n++){
                    first = true;
                    double distance = 0;
            double max = 0;
            double min = 0;
                    for(Song song : songs){
                        if(first){
                            max = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[n];
                            min = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[n];
                            first = false;
                        }else{
                            max = Math.max(max, song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[n]);
                            min = Math.min(min, song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[n]);
                        }
                        
                    }
                     System.out.println("Distance for average n["+n+"]: "+Math.abs(max-min)+", max: "+max+", min: "+min);
            }
               
        }
    }
    
    public void printClusterFactorDes(){
        System.out.println("Cluster: "+this.getGenre());
        for(Integer integ : factor.keySet()){
            System.out.println("Key:"+integ);
            for(String desName : factor.get(integ).getDescriptors().keySet()){
                System.out.println("desName: "+desName+", value: "+factor.get(integ).getDescriptors().get(desName));
            }
        }
    }
    
}
