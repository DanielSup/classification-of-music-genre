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

    public ClusterManager(Model model) {
        this.model = model;
    }

    public ClusteringResult computeClusteringResultOneToOne() {
        if (model.getComputingSong() != null) {
            ClusteringResult result = new ClusteringResult();
            this.computeClusteringFactorOneToOne();
            for (int i = 0; i < model.getComputingSong().getSongPartsComputedDescriptor().size(); i++) {
                HashMap<String, ClusterDescriptor> songPartDesc = new HashMap<String, ClusterDescriptor>();
                boolean first = true;
                for (Cluster cluster : model.getClusters().values()) {

                    for (Song song : cluster.getSongs()) {
                        if (first) {
                            for (int a = 0; a < 13; a++) {
                                songPartDesc.put("average" + a, new ClusterDescriptor(song, "average", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]), cluster.getFactor().get(i).getDescriptors().get("average" + a)));
                                songPartDesc.put("downDistance" + a, new ClusterDescriptor(song, "downDistance", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]), cluster.getFactor().get(i).getDescriptors().get("downDistance" + a)));
                                songPartDesc.put("hopsNumber" + a, new ClusterDescriptor(song, "hopsNumber", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]), cluster.getFactor().get(i).getDescriptors().get("hopsNumber" + a)));
                                songPartDesc.put("max" + a, new ClusterDescriptor(song, "max", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]), cluster.getFactor().get(i).getDescriptors().get("max" + a)));
                                songPartDesc.put("min" + a, new ClusterDescriptor(song, "min", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]), cluster.getFactor().get(i).getDescriptors().get("min" + a)));
                                songPartDesc.put("upDistance" + a, new ClusterDescriptor(song, "upDistance", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]), cluster.getFactor().get(i).getDescriptors().get("upDistance" + a)));
                                songPartDesc.put("wholeDistance" + a, new ClusterDescriptor(song, "wholeDistance", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]), cluster.getFactor().get(i).getDescriptors().get("wholeDistance" + a)));
                            }
                            first = false;
                        } else {
                            for (int a = 0; a < 13; a++) {
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]) < songPartDesc.get("average" + a).getValue()) {
                                    songPartDesc.get("average" + a).setSong(song);
                                    songPartDesc.get("average" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("average" + a));
                                    songPartDesc.get("average" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a]));
                                }
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]) < songPartDesc.get("downDistance" + a).getValue()) {
                                    songPartDesc.get("downDistance" + a).setSong(song);
                                    songPartDesc.get("downDistance" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("downDistance" + a));
                                    songPartDesc.get("downDistance" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a]));
                                }
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]) < songPartDesc.get("hopsNumber" + a).getValue()) {
                                    songPartDesc.get("hopsNumber" + a).setSong(song);
                                    songPartDesc.get("hopsNumber" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("hopsNumber" + a));
                                    songPartDesc.get("hopsNumber" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a]));
                                }
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]) < songPartDesc.get("max" + a).getValue()) {
                                    songPartDesc.get("max" + a).setSong(song);
                                    songPartDesc.get("max" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("max" + a));
                                    songPartDesc.get("max" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a]));
                                }
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]) < songPartDesc.get("min" + a).getValue()) {
                                    songPartDesc.get("min" + a).setSong(song);
                                    songPartDesc.get("min" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("min" + a));
                                    songPartDesc.get("min" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a]));
                                }
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]) < songPartDesc.get("upDistance" + a).getValue()) {
                                    songPartDesc.get("upDistance" + a).setSong(song);
                                    songPartDesc.get("upDistance" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("upDistance" + a));
                                    songPartDesc.get("upDistance" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a]));
                                }
                                if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]) < songPartDesc.get("wholeDistance" + a).getValue()) {
                                    songPartDesc.get("wholeDistance" + a).setSong(song);
                                    songPartDesc.get("wholeDistance" + a).setDescriptorFactor(cluster.getFactor().get(i).getDescriptors().get("wholeDistance" + a));
                                    songPartDesc.get("wholeDistance" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a] - song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a]));
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

    public ClusteringResult computeClusteringResultOneToMany() {

        if (model.getComputingSong() != null) {
            this.computeClusteringFactorOneToMany();
            ClusteringResult result = new ClusteringResult();
            int tmpPartNum;
            for (int i = 0; i < model.getComputingSong().getSongPartsComputedDescriptor().size(); i++) {
                HashMap<String, ClusterDescriptor> songPartDesc = new HashMap<String, ClusterDescriptor>();
                boolean first = true;
                for (Cluster cluster : model.getClusters().values()) {

                    for (Song song : cluster.getSongs()) {
                        for (SongPart songPart : song.getSongPartsComputedDescriptor()) {
                            tmpPartNum = 0;
                            if (first) {
                                for (int a = 0; a < 13; a++) {
                                    songPartDesc.put("average" + a, new ClusterDescriptor(song, "average", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a] - songPart.getDescriptor().getAverage()[a]), cluster.getFactor().get(0).getDescriptors().get("average" + a)));
                                    songPartDesc.put("downDistance" + a, new ClusterDescriptor(song, "downDistance", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a] - songPart.getDescriptor().getDownDistance()[a]), cluster.getFactor().get(0).getDescriptors().get("downDistance" + a)));
                                    songPartDesc.put("hopsNumber" + a, new ClusterDescriptor(song, "hopsNumber", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a] - songPart.getDescriptor().getHopsNumber()[a]), cluster.getFactor().get(0).getDescriptors().get("hopsNumber" + a)));
                                    songPartDesc.put("max" + a, new ClusterDescriptor(song, "max", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a] - songPart.getDescriptor().getMax()[a]), cluster.getFactor().get(0).getDescriptors().get("max" + a)));
                                    songPartDesc.put("min" + a, new ClusterDescriptor(song, "min", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a] - songPart.getDescriptor().getMin()[a]), cluster.getFactor().get(0).getDescriptors().get("min" + a)));
                                    songPartDesc.put("upDistance" + a, new ClusterDescriptor(song, "upDistance", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a] - songPart.getDescriptor().getUpDistance()[a]), cluster.getFactor().get(0).getDescriptors().get("upDistance" + a)));
                                    songPartDesc.put("wholeDistance" + a, new ClusterDescriptor(song, "wholeDistance", a, Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a] - songPart.getDescriptor().getWholeDistance()[a]), cluster.getFactor().get(0).getDescriptors().get("wholeDistance" + a)));
                                }
                                first = false;
                            } else {
                                for (int a = 0; a < 13; a++) {
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a] - songPart.getDescriptor().getAverage()[a]) < songPartDesc.get("average" + a).getValue()) {
                                        songPartDesc.get("average" + a).setSong(song);
                                        songPartDesc.get("average" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("average" + a));
                                        songPartDesc.get("average" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[a] - songPart.getDescriptor().getAverage()[a]));
                                    }
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a] - songPart.getDescriptor().getDownDistance()[a]) < songPartDesc.get("downDistance" + a).getValue()) {
                                        songPartDesc.get("downDistance" + a).setSong(song);
                                        songPartDesc.get("downDistance" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("downDistance" + a));
                                        songPartDesc.get("downDistance" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[a] - songPart.getDescriptor().getDownDistance()[a]));
                                    }
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a] - songPart.getDescriptor().getHopsNumber()[a]) < songPartDesc.get("hopsNumber" + a).getValue()) {
                                        songPartDesc.get("hopsNumber" + a).setSong(song);
                                        songPartDesc.get("hopsNumber" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("hopsNumber" + a));
                                        songPartDesc.get("hopsNumber" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[a] - songPart.getDescriptor().getHopsNumber()[a]));
                                    }
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a] - songPart.getDescriptor().getMax()[a]) < songPartDesc.get("max" + a).getValue()) {
                                        songPartDesc.get("max" + a).setSong(song);
                                        songPartDesc.get("max" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("max" + a));
                                        songPartDesc.get("max" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[a] - songPart.getDescriptor().getMax()[a]));
                                    }
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a] - songPart.getDescriptor().getMin()[a]) < songPartDesc.get("min" + a).getValue()) {
                                        songPartDesc.get("min" + a).setSong(song);
                                        songPartDesc.get("min" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("min" + a));
                                        songPartDesc.get("min" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[a] - songPart.getDescriptor().getMin()[a]));
                                    }
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a] - songPart.getDescriptor().getUpDistance()[a]) < songPartDesc.get("upDistance" + a).getValue()) {
                                        songPartDesc.get("upDistance" + a).setSong(song);
                                        songPartDesc.get("upDistance" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("upDistance" + a));
                                        songPartDesc.get("upDistance" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[a] - songPart.getDescriptor().getUpDistance()[a]));
                                    }
                                    if (Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a] - songPart.getDescriptor().getWholeDistance()[a]) < songPartDesc.get("wholeDistance" + a).getValue()) {
                                        songPartDesc.get("wholeDistance" + a).setSong(song);
                                        songPartDesc.get("wholeDistance" + a).setDescriptorFactor(cluster.getFactor().get(0).getDescriptors().get("wholeDistance" + a));
                                        songPartDesc.get("wholeDistance" + a).setValue(Math.abs(model.getComputingSong().getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[a] - songPart.getDescriptor().getWholeDistance()[a]));
                                    }
                                }
                            }
                            tmpPartNum++;
                        }
                    }
                }
                result.getResults().add(songPartDesc);
            }
            return result;
        }
        return null;
    }

    private void computeClusteringFactorOneToOne() {
        double[][][][] min = new double[model.getClusters().values().size()][model.getComputingSong().getSongPartsComputedDescriptor().size()][13][7];
        double[][][][] max = new double[model.getClusters().values().size()][model.getComputingSong().getSongPartsComputedDescriptor().size()][13][7];
        double[][][][] vzd = new double[model.getClusters().values().size()][model.getComputingSong().getSongPartsComputedDescriptor().size()][13][7];
        double[][][] totalVzd = new double[model.getComputingSong().getSongPartsComputedDescriptor().size()][13][7];
        int clusterCount = 0;
        boolean first = true;
        for (int i = 0; i < model.getComputingSong().getSongPartsComputedDescriptor().size(); i++) {
            clusterCount = 0;
            for (Cluster cluster : model.getClusters().values()) {
                first = true;
                for (Song song : cluster.getSongs()) {
                        for (int c = 0; c < 13; c++) {
                            if (first) {
                                min[clusterCount][i][c][0] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[c];
                                min[clusterCount][i][c][1] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[c];
                                min[clusterCount][i][c][2] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[c];
                                min[clusterCount][i][c][3] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[c];
                                min[clusterCount][i][c][4] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[c];
                                min[clusterCount][i][c][5] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[c];
                                min[clusterCount][i][c][6] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[c];
                                max[clusterCount][i][c][0] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[c];
                                max[clusterCount][i][c][1] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[c];
                                max[clusterCount][i][c][2] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[c];
                                max[clusterCount][i][c][3] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[c];
                                max[clusterCount][i][c][4] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[c];
                                max[clusterCount][i][c][5] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[c];
                                max[clusterCount][i][c][6] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[c];
                                first = false;
                            } else {
                                if (min[clusterCount][i][c][0] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[c]) {
                                    min[clusterCount][i][c][0] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[c];
                                }
                                if (min[clusterCount][i][c][1] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[c]) {
                                    min[clusterCount][i][c][1] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[c];
                                }
                                if (min[clusterCount][i][c][2] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[c]) {
                                    min[clusterCount][i][c][2] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[c];
                                }
                                if (min[clusterCount][i][c][3] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[c]) {
                                    min[clusterCount][i][c][3] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[c];
                                }
                                if (min[clusterCount][i][c][4] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[c]) {
                                    min[clusterCount][i][c][4] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[c];
                                }
                                if (min[clusterCount][i][c][5] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[c]) {
                                    min[clusterCount][i][c][5] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[c];
                                }
                                if (min[clusterCount][i][c][6] > song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[c]) {
                                    min[clusterCount][i][c][6] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[c];
                                }
                                if (max[clusterCount][i][c][0] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[c]) {
                                    max[clusterCount][i][c][0] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getAverage()[c];
                                }
                                if (max[clusterCount][i][c][1] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[c]) {
                                    max[clusterCount][i][c][1] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getDownDistance()[c];
                                }
                                if (max[clusterCount][i][c][2] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[c]) {
                                    max[clusterCount][i][c][2] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getHopsNumber()[c];
                                }
                                if (max[clusterCount][i][c][3] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[c]) {
                                    max[clusterCount][i][c][3] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMax()[c];
                                }
                                if (max[clusterCount][i][c][4] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[c]) {
                                    max[clusterCount][i][c][4] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getMin()[c];
                                }
                                if (max[clusterCount][i][c][5] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[c]) {
                                    max[clusterCount][i][c][5] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getUpDistance()[c];
                                }
                                if (max[clusterCount][i][c][6] < song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[c]) {
                                    max[clusterCount][i][c][6] = song.getSongPartsComputedDescriptor().get(i).getDescriptor().getWholeDistance()[c];
                                }
                            }
                        }
                    
                }
                for (int c = 0; c < 13; c++) {
                    for (int d = 0; d < 7; d++) {
                        vzd[clusterCount][i][c][d] = Math.abs(max[clusterCount][i][c][d] - min[clusterCount][i][c][d]);
                        totalVzd[i][c][d] += vzd[clusterCount][i][c][d];
                    }
                }
                clusterCount++;
            }
            clusterCount = 0;
            for (Cluster cluster : model.getClusters().values()) {               
                ClusterFactorDescriptor cfd = new ClusterFactorDescriptor();
                for (int c = 0; c < 13; c++) {
                    cfd.getDescriptors().put("average" + c, vzd[clusterCount][i][c][0] / totalVzd[i][c][0]);
                    cfd.getDescriptors().put("downDistance" + c, vzd[clusterCount][i][c][1] / totalVzd[i][c][1]);
                    cfd.getDescriptors().put("hopsNumber" + c, vzd[clusterCount][i][c][2] / totalVzd[i][c][2]);
                    cfd.getDescriptors().put("max" + c, vzd[clusterCount][i][c][3] / totalVzd[i][c][3]);
                    cfd.getDescriptors().put("min" + c, vzd[clusterCount][i][c][4] / totalVzd[i][c][4]);
                    cfd.getDescriptors().put("upDistance" + c, vzd[clusterCount][i][c][5] / totalVzd[i][c][5]);
                    cfd.getDescriptors().put("wholeDistance" + c, vzd[clusterCount][i][c][6] / totalVzd[i][c][6]);
                }
                cluster.getFactor().put(i, cfd);
                clusterCount++;
            }
        }
        
    }

    private void computeClusteringFactorOneToMany() {
        double[][][] min = new double[model.getClusters().values().size()][13][7];
        double[][][] max = new double[model.getClusters().values().size()][13][7];
        double[][][] vzd = new double[model.getClusters().values().size()][13][7];
        double[][] totalVzd = new double[13][7];
        int clusterCount = 0;
        boolean first = true;
            clusterCount = 0;
            for (Cluster cluster : model.getClusters().values()) {
                first = true;
                for (Song song : cluster.getSongs()) {
                    for (SongPart songPart : song.getSongPartsComputedDescriptor()) {
                        for (int c = 0; c < 13; c++) {
                            if (first) {
                                min[clusterCount][c][0] = songPart.getDescriptor().getAverage()[c];
                                min[clusterCount][c][1] = songPart.getDescriptor().getDownDistance()[c];
                                min[clusterCount][c][2] = songPart.getDescriptor().getHopsNumber()[c];
                                min[clusterCount][c][3] = songPart.getDescriptor().getMax()[c];
                                min[clusterCount][c][4] = songPart.getDescriptor().getMin()[c];
                                min[clusterCount][c][5] = songPart.getDescriptor().getUpDistance()[c];
                                min[clusterCount][c][6] = songPart.getDescriptor().getWholeDistance()[c];
                                max[clusterCount][c][0] = songPart.getDescriptor().getAverage()[c];
                                max[clusterCount][c][1] = songPart.getDescriptor().getDownDistance()[c];
                                max[clusterCount][c][2] = songPart.getDescriptor().getHopsNumber()[c];
                                max[clusterCount][c][3] = songPart.getDescriptor().getMax()[c];
                                max[clusterCount][c][4] = songPart.getDescriptor().getMin()[c];
                                max[clusterCount][c][5] = songPart.getDescriptor().getUpDistance()[c];
                                max[clusterCount][c][6] = songPart.getDescriptor().getWholeDistance()[c];
                                first = false;
                            } else {
                                if (min[clusterCount][c][0] > songPart.getDescriptor().getAverage()[c]) {
                                    min[clusterCount][c][0] = songPart.getDescriptor().getAverage()[c];
                                }
                                if (min[clusterCount][c][1] > songPart.getDescriptor().getDownDistance()[c]) {
                                    min[clusterCount][c][1] = songPart.getDescriptor().getDownDistance()[c];
                                }
                                if (min[clusterCount][c][2] > songPart.getDescriptor().getHopsNumber()[c]) {
                                    min[clusterCount][c][2] = songPart.getDescriptor().getHopsNumber()[c];
                                }
                                if (min[clusterCount][c][3] > songPart.getDescriptor().getMax()[c]) {
                                    min[clusterCount][c][3] = songPart.getDescriptor().getMax()[c];
                                }
                                if (min[clusterCount][c][4] > songPart.getDescriptor().getMin()[c]) {
                                    min[clusterCount][c][4] = songPart.getDescriptor().getMin()[c];
                                }
                                if (min[clusterCount][c][5] > songPart.getDescriptor().getUpDistance()[c]) {
                                    min[clusterCount][c][5] = songPart.getDescriptor().getUpDistance()[c];
                                }
                                if (min[clusterCount][c][6] > songPart.getDescriptor().getWholeDistance()[c]) {
                                    min[clusterCount][c][6] = songPart.getDescriptor().getWholeDistance()[c];
                                }
                                if (max[clusterCount][c][0] < songPart.getDescriptor().getAverage()[c]) {
                                    max[clusterCount][c][0] = songPart.getDescriptor().getAverage()[c];
                                }
                                if (max[clusterCount][c][1] < songPart.getDescriptor().getDownDistance()[c]) {
                                    max[clusterCount][c][1] = songPart.getDescriptor().getDownDistance()[c];
                                }
                                if (max[clusterCount][c][2] < songPart.getDescriptor().getHopsNumber()[c]) {
                                    max[clusterCount][c][2] = songPart.getDescriptor().getHopsNumber()[c];
                                }
                                if (max[clusterCount][c][3] < songPart.getDescriptor().getMax()[c]) {
                                    max[clusterCount][c][3] = songPart.getDescriptor().getMax()[c];
                                }
                                if (max[clusterCount][c][4] < songPart.getDescriptor().getMin()[c]) {
                                    max[clusterCount][c][4] = songPart.getDescriptor().getMin()[c];
                                }
                                if (max[clusterCount][c][5] < songPart.getDescriptor().getUpDistance()[c]) {
                                    max[clusterCount][c][5] = songPart.getDescriptor().getUpDistance()[c];
                                }
                                if (max[clusterCount][c][6] < songPart.getDescriptor().getWholeDistance()[c]) {
                                    max[clusterCount][c][6] = songPart.getDescriptor().getWholeDistance()[c];
                                }
                            }
                        }
                    }
                }
                for (int c = 0; c < 13; c++) {
                    for (int d = 0; d < 7; d++) {
                        vzd[clusterCount][c][d] = Math.abs(max[clusterCount][c][d] - min[clusterCount][c][d]);
                        totalVzd[c][d] += vzd[clusterCount][c][d];
                    }
                }
                clusterCount++;
            }
            clusterCount = 0;
            for (Cluster cluster : model.getClusters().values()) {                
                ClusterFactorDescriptor cfd = new ClusterFactorDescriptor();
                for (int c = 0; c < 13; c++) {
                    cfd.getDescriptors().put("average" + c, vzd[clusterCount][c][0] / totalVzd[c][0]);
                    cfd.getDescriptors().put("downDistance" + c, vzd[clusterCount][c][1] / totalVzd[c][1]);
                    cfd.getDescriptors().put("hopsNumber" + c, vzd[clusterCount][c][2] / totalVzd[c][2]);
                    cfd.getDescriptors().put("max" + c, vzd[clusterCount][c][3] / totalVzd[c][3]);
                    cfd.getDescriptors().put("min" + c, vzd[clusterCount][c][4] / totalVzd[c][4]);
                    cfd.getDescriptors().put("upDistance" + c, vzd[clusterCount][c][5] / totalVzd[c][5]);
                    cfd.getDescriptors().put("wholeDistance" + c, vzd[clusterCount][c][6] / totalVzd[c][6]);
                }
                cluster.getFactor().put(0, cfd);
                clusterCount++;
            }
        }
    
}
