/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import soundDetector.clustering.Cluster;
import soundDetector.data.Model;
import soundDetector.descriptor.Descriptor;
import soundDetector.song.Song;
import soundDetector.song.SongPart;

/**
 *
 * @author Brka
 */
public class FileManager {

    private Model model;

    public FileManager(Model model) {
        this.model = model;
    }

    public void exportToFile(String path) {
        JSONObject obj = new JSONObject();
        for (Cluster cluster : model.getClusters().values()) {
            JSONObject jsonCluster = new JSONObject();
            for (Song song : cluster.getSongs()) {
                JSONObject jsonSong = new JSONObject();
                int tmp = 0;
                for (SongPart songPart : song.getSongPartsComputedDescriptor()) {
                    JSONObject jsonSongPart = new JSONObject();
                    for (int i = 0; i < 13; i++) {
                        JSONObject jsonDescriptor = new JSONObject();
                        jsonDescriptor.put("Min", songPart.getDescriptor().getMin()[i]);
                        jsonDescriptor.put("Max", songPart.getDescriptor().getMax()[i]);
                        jsonDescriptor.put("Average", songPart.getDescriptor().getAverage()[i]);
                        jsonDescriptor.put("HopsNumber", songPart.getDescriptor().getHopsNumber()[i]);
                        jsonDescriptor.put("DownDistance", songPart.getDescriptor().getDownDistance()[i]);
                        jsonDescriptor.put("WholeDistance", songPart.getDescriptor().getWholeDistance()[i]);
                        jsonDescriptor.put("UpDistance", songPart.getDescriptor().getUpDistance()[i]);
                        jsonSongPart.put(i, jsonDescriptor);
                    }
                    jsonSongPart.put("StartTime", songPart.getStartTime());
                    jsonSong.put(tmp, jsonSongPart);
                    tmp++;
                }
                jsonCluster.put(song.getName(), jsonSong);
            }
            obj.put(cluster.getGenre(), jsonCluster);
        }
        try (FileWriter file = new FileWriter(path)) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importFromFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object jsonClust : jsonObject.keySet()) {
                JSONObject clusters = (JSONObject) jsonObject.get(jsonClust);
                Cluster cluster = new Cluster((String) jsonClust);
                for (Object jsonSong : clusters.keySet()) {
                    JSONObject jsonSongs = (JSONObject) clusters.get(jsonSong);
                    Song song = new Song((String) jsonSong, (String) jsonClust);
                    for (Object jsonSongPart : jsonSongs.keySet()) {
                        JSONObject jsonSongParts = (JSONObject) jsonSongs.get(jsonSongPart);
                        double[] min = new double[13];
                        double[] max = new double[13];
                        double[] average = new double[13];
                        double[] wholeDistance = new double[13];
                        double[] upDistance = new double[13];
                        double[] downDistance = new double[13];
                        int[] hopsNumber = new int[13];
                        for (int i=0;i<13;i++){
                            System.out.println("jsonDescriptor : "+i);
                            JSONObject jsonDescriptors = (JSONObject) jsonSongParts.get(String.valueOf(i));
                            System.out.println("jsonDescriptors : "+jsonDescriptors);
                            min[i] = (double) jsonDescriptors.get("Min");
                            max[i] = (double) jsonDescriptors.get("Max");
                            average[i] = (double) jsonDescriptors.get("Average");
                            wholeDistance[i] = (double) jsonDescriptors.get("WholeDistance");
                            upDistance[i] = (double) jsonDescriptors.get("UpDistance");
                            downDistance[i] = (double) jsonDescriptors.get("DownDistance");
                            System.out.println("valueHops:"+((long) jsonDescriptors.get("HopsNumber"))+", song:"+song.getName()+", genre:"+song.getGenre()+",songPart:"+((String) jsonSongPart));
                            hopsNumber[i] = (int) (long) jsonDescriptors.get("HopsNumber");
                        }
                        Descriptor descriptor = new Descriptor(max,min,average,wholeDistance,upDistance,downDistance,hopsNumber);
                        SongPart songPart = new SongPart((int) (long) jsonSongParts.get("StartTime"));
                        songPart.setDescriptor(descriptor);
                        song.getSongParts().add(songPart);
                        song.getSongPartsComputedDescriptor().add(songPart);
                    }
                    song.sortAllSongParts();
                    cluster.getSongs().add(song);
                }
                model.getClusters().put((String) jsonClust, cluster);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
