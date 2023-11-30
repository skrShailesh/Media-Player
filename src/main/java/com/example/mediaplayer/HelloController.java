package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    private String filePath;

    @FXML
    protected void onHelloButtonClick(ActionEvent event)
    {
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("Select a File(*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file= fileChooser.showOpenDialog(null);
        filePath= file.toURI().toString();

        if (filePath !=null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();

            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));


            mediaPlayer.play();

        }
    }

    @FXML
    protected void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    @FXML
    protected void playVideo(ActionEvent event){
        mediaPlayer.play();
    }
    @FXML
    protected void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    @FXML
    protected void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }
    @FXML
    protected void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2.0);
    }
    @FXML
    protected void slowVideo(ActionEvent event){
        mediaPlayer.setRate(.75);
    }
    @FXML
    protected void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(.5);
    }
    @FXML
    protected void exitVideo(ActionEvent event){
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}

















































