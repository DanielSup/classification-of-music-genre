/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundDetector.song;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openimaj.audio.SampleChunk;
import soundDetector.clustering.Cluster;
import soundDetector.data.Model;

/**
 *
 * @author Brka
 */
public class SongManager {

    private final int chunksPerSecond = 38;
    private Model model;

    public SongManager(Model model) {
        this.model = model;
    }

    /**
     * Upload all songs to model
     * @return number of uploaded songs
     */
    public int uploadSongs() {
        int tmpCount = 0;
        File[] genres = new File("../genres/").listFiles();
        for (File genre : genres) {
            File[] songs = genre.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".mp3");
                }
            });
            if(!model.getClusters().containsKey(genre.getName())){
                model.getClusters().put(genre.getName(), new Cluster(genre.getName()));
            }
            for (File song : songs) {
                System.out.println("Song name: "+song.getName()+", song genre: "+song.getParentFile().getName());
                model.getClusters().get(genre.getName()).getSongs().add(new Song(song));
                tmpCount++;
                
            }
        }
        return tmpCount;
    }
    
    public void uploadComputingSong(File file){
        model.setComputingSong(new Song(file));
    }

    /**
     * 
     * @param duration duration in seconds for trimming
     */
    public void trimSongs(int duration) {
        int durationCounter;
        int timeCounter;
        for (Cluster cluster : model.getClusters().values()){
        for (Song song : cluster.getSongs()) {
            if (song.getSongParts().isEmpty()) {
                SampleChunk sc = null;
                timeCounter = 0;
                System.out.println("Trimming song: "+song.getName()+", genre: "+song.getGenre());
                while ((sc = song.getAudio().nextSampleChunk()) != null) {
                    durationCounter = 0;
                    SongPart songPart = new SongPart(timeCounter);
                    while ((durationCounter < duration * chunksPerSecond) && (sc != null)) {
                        songPart.getChunks().add(sc.clone());
                        sc = song.getAudio().nextSampleChunk();
                        durationCounter++;
                        timeCounter++;
                    }
                    song.getSongParts().add(songPart);
                }
            }
        }
    }
    }
    public void trimSong(Song song, int duration){
        int durationCounter;
        int timeCounter;
            if (song.getSongParts().isEmpty()) {
                SampleChunk sc = null;
                timeCounter = 0;
                System.out.println("Trimming song: "+song.getName()+", genre: "+song.getGenre());
                while ((sc = song.getAudio().nextSampleChunk()) != null) {
                    durationCounter = 0;
                    SongPart songPart = new SongPart(timeCounter);
                    while ((durationCounter < duration * chunksPerSecond) && (sc != null)) {
                        songPart.getChunks().add(sc.clone());
                        sc = song.getAudio().nextSampleChunk();
                        durationCounter++;
                        timeCounter++;
                    }
                    song.getSongParts().add(songPart);
                }
            }
    song.setAudio(null);
    }
    
    public void trimComputingSong(int duration){
        int durationCounter;
        int timeCounter;
                SampleChunk sc = null;
                timeCounter = 0;
                System.out.println("Trimming song: "+model.getComputingSong().getName()+", genre: "+model.getComputingSong().getGenre());
                while ((sc = model.getComputingSong().getAudio().nextSampleChunk()) != null) {
                    durationCounter = 0;
                    SongPart songPart = new SongPart(timeCounter);
                    while ((durationCounter < duration * chunksPerSecond) && (sc != null)) {
                        songPart.getChunks().add(sc.clone());
                        sc = model.getComputingSong().getAudio().nextSampleChunk();
                        durationCounter++;
                        timeCounter++;
                    }
                    model.getComputingSong().getSongParts().add(songPart);
                }
            
        }
}
