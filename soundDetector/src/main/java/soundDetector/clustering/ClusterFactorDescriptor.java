/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.clustering;

import java.util.HashMap;

/**
 *
 * @author Brka
 */
class ClusterFactorDescriptor {
    private HashMap<String,Double> descriptors;
    public ClusterFactorDescriptor(){
        descriptors = new HashMap<String,Double>();
    }

    public HashMap<String, Double> getDescriptors() {
        return descriptors;
    }
    
}
