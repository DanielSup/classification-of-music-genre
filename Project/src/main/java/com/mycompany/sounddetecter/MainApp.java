package com.mycompany.sounddetecter;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import de.crysandt.audio.mpeg7audio.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.ParserConfigurationException;
import model.Model;
import org.w3c.dom.Document;


public class MainApp extends Application {
    private Model model;
    @Override
    public void start(Stage stage) throws Exception {
        
        Model model = new Model();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = loader.load();
        FXMLController controller = loader.<FXMLController>getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("SoundDetecter");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Config config = new ConfigDefault();
            File file = new File("C:\\Users\\Michal\\Downloads\\eiffel65_blue.wav");
            AudioInputStream ais = null;
            if(file.exists()){
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                InputStream is = classloader.getResourceAsStream("C:\\Users\\Michal\\Downloads\\eiffel65_blue.wav");
                System.out.println("file exists");
                try {
                    
                    ais = AudioSystem.getAudioInputStream(file);
                    //AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("NOT");
            }
            System.out.println("Hotovo");
            Document mpeg7 = MP7DocumentBuilder.encode(ais, config);
            System.out.println("mp7 hotovo");
            launch(args);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
