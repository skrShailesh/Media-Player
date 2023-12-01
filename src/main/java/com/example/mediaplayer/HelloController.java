package com.example.mediaplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    private String filePath;

    @FXML
    private Slider slider;
    @FXML
    private Slider seekSlider;

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

            slider.setValue(mediaPlayer.getVolume()*100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                }
            });


                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                        seekSlider.setValue(t1.toSeconds());
                    }
                });

                seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                    }
                });


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
        mediaPlayer.setRate(1);
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

















































