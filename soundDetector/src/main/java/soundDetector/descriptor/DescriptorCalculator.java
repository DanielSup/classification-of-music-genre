/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.descriptor;

import org.openimaj.audio.SampleChunk;
import org.openimaj.audio.features.MFCC;
import org.openimaj.video.xuggle.XuggleAudio;

/**
 *
 * @author Brka
 * Class for cumputing simple Descriptor
 */
public class DescriptorCalculator {

    public DescriptorCalculator() {
    }
    /**
     * 
     * @param audio the input object for which the "Descriptor" will be computed
     * @return Descriptor object with computed values
     */
    public Descriptor computeDescriptor(XuggleAudio audio){
        MFCC mfcc = new MFCC(audio); 
        SampleChunk sc = null;
        boolean first = true;
        boolean [] stateInfo = new boolean[13];
        double [] prev = new double[13];
        double [] max = new double[13];
        double [][] mfccs = null;
        double [] min = new double[13];
        double [] sum = new double[13];
        double [] average = new double[13];
        double [] wholeDistance = new double[13];
        double [] upDistance = new double[13];
        double [] downDistance = new double[13];
        int [] hopsNumber = new int[13];
        int count = 0;
        while ((sc = mfcc.nextSampleChunk()) != null) {
            mfccs = mfcc.getLastCalculatedFeature();
            for(int i = 0; i < mfccs.length; i++){
                count++;
                for(int n = 0; n < mfccs[i].length; n ++){
            if(first){
                first = false;
                prev[n] = mfccs[i][n];
                max[n] = mfccs[i][n];
                min[n] = mfccs[i][n];
                sum[n] = mfccs[i][n];
                wholeDistance[n] = 0.0;
                upDistance[n] = 0.0;
                downDistance[n] = 0.0;
                hopsNumber[n] = 0;
            }else{
                max[n] = Math.max(max[n], mfccs[i][n]);
                min[n] = Math.min(min[n], mfccs[i][n]);
                sum[n] += mfccs[i][n];
                wholeDistance[n] += Math.abs(prev[n]-mfccs[i][n]);
                
                if(count==2){
                    if(prev[n]<mfccs[i][n]){
                    upDistance[n] += Math.abs(mfccs[i][n]-prev[n]);
                    stateInfo[n] = true;
                }else if(prev[n]>mfccs[i][n]){
                    downDistance[n] += Math.abs(prev[n]-mfccs[i][n]);
                    stateInfo[n] = false;
                }
                }else{
                    if(prev[n]<mfccs[i][n]){
                    upDistance[n] += Math.abs(mfccs[i][n]-prev[n]);
                    if(!stateInfo[n]){
                       hopsNumber[n]++;
                       stateInfo[n] = true;
                    }
                }else if(prev[n]>mfccs[i][n]){
                    downDistance[n] += Math.abs(prev[n]-mfccs[i][n]);
                    if(stateInfo[n]){
                        hopsNumber[n]++;
                        stateInfo[n] = false;
                    }
                }
                }
                prev[n] = mfccs[i][n];
            }
            }
            }
        }
        for(int i = 0; i < 13; i++){
            average[i] = sum[i]/count;
        }
        Descriptor des = new Descriptor(max,min,average,wholeDistance,upDistance,downDistance,hopsNumber);
        return des;
    }
}
