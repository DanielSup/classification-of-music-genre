/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.clustering;

import java.util.ArrayList;
import java.util.HashMap;
import soundDetector.data.Model;
import soundDetector.song.Song;
import soundDetector.song.SongPart;

/**
 *
 * @author Brka
 */
public class ClusterManager {
    private Model model;
    public ClusterManager(Model model){
        this.model = model;
    }
    
    public ClusteringResult computeClusteringResult(){
        
        if(model.getComputingSong()!=null){
            ClusteringResult result = new ClusteringResult();
            
            for(int i=0;i<model.getComputingSong().getSongPartsComputedDescriptor().size();i++){
                HashMap<String,ClusterDescriptor> songPartDesc = new HashMap<String,ClusterDescriptor>();
                boolean first = true;
                for(Cluster cluster : model.getClusters().values()){
                    
                    for(Song song : cluster.getSongs()){
                        if(first){
                            for(int a=0;a<13;a++){
                                songPartDesc.put("average"+a,new ClusterDescriptor(song,"average",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a])));
                                songPartDesc.put("downDistance"+a,new ClusterDescriptor(song,"downDistance",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a])));
                                songPartDesc.put("hopsNumber"+a,new ClusterDescriptor(song,"hopsNumber",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a])));
                                songPartDesc.put("max"+a,new ClusterDescriptor(song,"max",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a])));
                                songPartDesc.put("min"+a,new ClusterDescriptor(song,"min",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a])));
                                songPartDesc.put("upDistance"+a,new ClusterDescriptor(song,"upDistance",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a])));
                                songPartDesc.put("wholeDistance"+a,new ClusterDescriptor(song,"wholeDistance",a,Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a])));
                            }
                            first = false;
                        }else{
                            for(int a=0;a<13;a++){
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a])<songPartDesc.get("average"+a).getValue()){
                                    songPartDesc.get("average"+a).setSong(song);
                                    songPartDesc.get("average"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]));
                                }
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a])<songPartDesc.get("downDistance"+a).getValue()){
                                    songPartDesc.get("downDistance"+a).setSong(song);
                                    songPartDesc.get("downDistance"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]));
                                }
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a])<songPartDesc.get("hopsNumber"+a).getValue()){
                                    songPartDesc.get("hopsNumber"+a).setSong(song);
                                    songPartDesc.get("hopsNumber"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]));
                                }
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a])<songPartDesc.get("max"+a).getValue()){
                                    songPartDesc.get("max"+a).setSong(song);
                                    songPartDesc.get("max"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]));
                                }
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a])<songPartDesc.get("min"+a).getValue()){
                                    songPartDesc.get("min"+a).setSong(song);
                                    songPartDesc.get("min"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]));
                                }
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a])<songPartDesc.get("upDistance"+a).getValue()){
                                    songPartDesc.get("upDistance"+a).setSong(song);
                                    songPartDesc.get("upDistance"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]));
                                }
                                if(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a])<songPartDesc.get("wholeDistance"+a).getValue()){
                                    songPartDesc.get("wholeDistance"+a).setSong(song);
                                    songPartDesc.get("wholeDistance"+a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]-song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]));
                                }
                                 }
                        }
                    }
                }
                result.getResults().add(songPartDesc);
            }
            return result;
        }
        return null;
    }
}
