/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.clustering;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Brka
 */
public class ClusteringResult {
    private ArrayList<HashMap<String,ClusterDescriptor>> results;
    private HashMap<String,Integer> resultMap;
    
    public ClusteringResult(){
        results = new ArrayList<HashMap<String,ClusterDescriptor>>();
    }

    public ArrayList<HashMap<String, ClusterDescriptor>> getResults() {
        return results;
    }
    public void printResults(){
        for(int i=0;i<results.size();i++){
            System.out.println("Song part: "+i+", resultSize: "+results.get(i).values().size());
            resultMap = new HashMap<String,Integer>();
            for(ClusterDescriptor des : results.get(i).values()){
                if(!resultMap.containsKey(des.getSong().getGenre())){
                    resultMap.put(des.getSong().getGenre(), 1);
                }else{
                    Integer tmp = resultMap.get(des.getSong().getGenre()) + 1;
                    resultMap.put(des.getSong().getGenre(), tmp);
                }
            }
            System.out.println("resultMap size:"+resultMap.size());
            for(String str : resultMap.keySet()){
                System.out.println(str+" : "+resultMap.get(str));
            }
            System.out.println("");
        }
    }
    
}
