/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.descriptor;

/**
 *
 * @author Brka
 */
public class Descriptor {
    /**
     * @max[i] max value for i-th descriptor
     * @min[i] min value for i-th descriptor
     * @average[i] average value for i-th descriptor
     * @wholeDistance[i] the distance from point to point for i-th descriptor 
     * @upDistance[i] only the up distance for i-th descriptor
     * @downDistance[i] only the down distance for i-th descriptor
     * @hopsNumber[i] the number of changes for i-th descriptor (from raising to falling/from falling to raising) 
     * wholeDistance = upDistance + downDistance
     */
    private double [] max;
    private double [] min;
    private double [] average;
    private double [] wholeDistance;
    private double [] upDistance;
    private double [] downDistance;
    private int [] hopsNumber;

    public Descriptor(double[] max, double[] min, double[] average, double[] wholeDistance, double[] upDistance, double[] downDistance, int[] hopsNumber) {
        this.max = max;
        this.min = min;
        this.average = average;
        this.wholeDistance = wholeDistance;
        this.upDistance = upDistance;
        this.downDistance = downDistance;
        this.hopsNumber = hopsNumber;
    }

    public double[] getMax() {
        return max;
    }

    public double[] getMin() {
        return min;
    }

    public double[] getAverage() {
        return average;
    }

    public double[] getWholeDistance() {
        return wholeDistance;
    }

    public double[] getUpDistance() {
        return upDistance;
    }

    public double[] getDownDistance() {
        return downDistance;
    }

    public int[] getHopsNumber() {
        return hopsNumber;
    }

    public void print(){
        for(int i=0;i<13;i++){
            System.out.println("Descriptor["+i+"], max="+max[i]+", min="+min[i]+", average="+average[i]+", wholeDistance="+wholeDistance[i]+", upDistance="+upDistance[i]+", downDistance="+downDistance[i]+", hopsNumber="+hopsNumber[i]);
        }
    }
    
    
}
