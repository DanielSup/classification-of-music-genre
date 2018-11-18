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
            for (File song : songs) {
                System.out.println("Song name: "+song.getName()+", song genre: "+song.getParentFile().getName());
                if (!model.getSongs().containsKey(song.getName())) {
                    model.getSongs().put(song.getName(), new Song(song));
                    tmpCount++;
                }
            }
        }
        return tmpCount;
    }

    public void trimSongs(int duration) {
        int durationCounter;
        int timeCounter;
        for (Song song : model.getSongs().values()) {
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
