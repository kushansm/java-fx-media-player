package lk.ijse.dep13.fx.controller;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class MainSceneController {
    public MediaView mdvwMediaView1;
    public AnchorPane root;
    public Slider sldrTime;
    public ImageView imgBackward;
    public ImageView imgPlay;
    public ImageView imgStop;
    public ImageView imgforward;
    public ImageView imgMute;
    public ImageView imgSound;
    public Label lblShow;

    private MediaPlayer mediaPlayer;

    public void imgBackwardOnMouseClicked(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            Duration currentTime = mediaPlayer.getCurrentTime();

            Duration rewindTime = currentTime.subtract(Duration.seconds(10));

            if (rewindTime.lessThan(Duration.ZERO)) {
                rewindTime = Duration.ZERO;
            }

            mediaPlayer.seek(rewindTime);
        }
    }

    public void imgPlayOnMouseClicked(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
            imgPlay.setImage(null);
        }
    }

    public void imgStopOnMouseClicked(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.getCurrentTime();

        }
    }

    public void imgforwardOnMouseClicked(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            Duration currentTime = mediaPlayer.getCurrentTime();

            Duration forwardTime = currentTime.add(Duration.seconds(10));

            Duration totalDuration = mediaPlayer.getTotalDuration();
            if (forwardTime.greaterThan(totalDuration)) {
                forwardTime = totalDuration;
            }

            mediaPlayer.seek(forwardTime);
        }
    }


    public void imgMuteOnMouseClicked(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.setMute(!mediaPlayer.isMute());
        }
    }

    public void imgSoundOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void lblShowOnMouseClicked(MouseEvent mouseEvent) {
        File file = new File("/home/kushan/Downloads/sample-30s.mp4");

        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }

        try {
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mdvwMediaView1.setMediaPlayer(mediaPlayer);

            mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                if (!sldrTime.isValueChanging()) {
                    sldrTime.setValue(newTime.toSeconds());
                }
            });

            mediaPlayer.setOnReady(() -> {
                sldrTime.setMax(mediaPlayer.getTotalDuration().toSeconds());
            });

            sldrTime.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (sldrTime.isValueChanging()) {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
            });

            mediaPlayer.play();
            lblShow.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initializing MediaPlayer: " + e.getMessage());
        }
    }



}
