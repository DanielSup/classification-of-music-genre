package com.mycompany.sounddetecter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import model.Model;

public class FXMLController implements Initializable {
    
    private static Model model;

    public void setModel(Model model) {
        this.model = model;
    }
    
    @FXML
    private PieChart pieChart;
    
    final FileChooser fileChooser = new FileChooser();
    
    @FXML
    private Button fileChooseButton;
    
    @FXML
    private Button computeCategoryButton;
    
    @FXML
    private Label selectedSong;
    
    @FXML
    private Label resultLabel;
    
    
    
    @FXML
    private void chooseFile(ActionEvent event) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(pieChart.getScene().getWindow());
        if(file!=null){
            selectedSong.setText(file.getName());
            model.setSelectedFile(file);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    private static void configureFileChooser(final FileChooser fileChooser) {      
            fileChooser.setTitle("Select MP3");
            File tmp;
            if(model.getSelectedFile()!=null){
                    tmp = model.getSelectedFile().getParentFile();
                }else{
                    tmp = new File(System.getProperty("user.home"));
                }
            fileChooser.setInitialDirectory(tmp);                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
            );
    }
}
