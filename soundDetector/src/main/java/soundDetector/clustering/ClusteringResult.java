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
    private HashMap<String,Double> resultMap;
    
    public ClusteringResult(){
        results = new ArrayList<HashMap<String,ClusterDescriptor>>();
    }

    public ArrayList<HashMap<String, ClusterDescriptor>> getResults() {
        return results;
    }
    public void printResults(){
        double tmpCount;
        double tmpMaxR = 0.0;
        Double tmp2 = 0.0;
        HashMap<String,Double> finalResult = new HashMap<String,Double>();
        for(int i=0;i<results.size();i++){
            tmpCount = 0.0;
            System.out.println("Song part: "+i+", resultSize: "+results.get(i).values().size());
            resultMap = new HashMap<String,Double>();
            for(ClusterDescriptor des : results.get(i).values()){
                tmpCount += des.getDescriptorFactor();
                if(!resultMap.containsKey(des.getSong().getGenre())){
                    resultMap.put(des.getSong().getGenre(), des.getDescriptorFactor());
                }else{
                    Double tmp = resultMap.get(des.getSong().getGenre()) + des.getDescriptorFactor();
                    resultMap.put(des.getSong().getGenre(), tmp);
                }
            }
            for(String str : resultMap.keySet()){
                System.out.println(str+": "+((resultMap.get(str)/tmpCount)*100)+"%");
                tmp2 = ((resultMap.get(str)/tmpCount)*100);
                if(finalResult.containsKey(str)){
                    tmp2 += (Double) finalResult.get(str);
                }
                finalResult.put(str,tmp2);
            }
            System.out.println("");
        }
        System.out.println("Final result: ");
        for(String str : finalResult.keySet()){
            tmpMaxR += finalResult.get(str);
        }
        for(String str : finalResult.keySet()){
            System.out.println(str+": "+((finalResult.get(str)/tmpMaxR)*100)+"%");
        }
        
        
    }
    
}
