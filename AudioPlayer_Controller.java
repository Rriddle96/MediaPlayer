import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;

public class AudioPlayer_Controller extends Application {

    private MediaPlayer mediaPlayer;
    private Label statusLabel;
    private VBox controlBox;
    private int backgroundIndex = 1;
    private Button playPauseButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Media Player");

        Button openButton = new Button("Open Audio File");
        statusLabel = new Label("Select a file to play.");
        
        playPauseButton = new Button("Play");
        Button restartButton = new Button("<<");
        Button changeBackgroundButton = new Button("Change Background");
   
        openButton.setOnAction(e -> openFile(primaryStage));  // Opens folder to select audio file from
        playPauseButton.setOnAction(e -> togglePlayPause()); // Toggles audio file to play or pause
        restartButton.setOnAction(e -> restart()); // replays the audio file
        changeBackgroundButton.setOnAction(e -> changeBackground()); // Changes background

        controlBox = new VBox(10, openButton, playPauseButton, restartButton, changeBackgroundButton, statusLabel);
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        
        root.getChildren().add(controlBox);
        
        primaryStage.show();
    }
    // allows the user to select an audio file from their file system using a graphical file chooser.
    private void openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav")); // FileChooser used to specify file types
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            loadAudio(file.toURI().toString());
            statusLabel.setText("Loaded: " + file.getName());
        }
    }
    // method is responsible for loading a new audio file into the media player.
    private void loadAudio(String filePath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        Media media = new Media(filePath);
        mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.setOnReady(() -> {
            playPauseButton.setText("Play");
            playPauseButton.setDisable(false);
        });
        
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaPlayer.seek(Duration.ZERO);
            playPauseButton.setText("Play");
        });
        
        playPauseButton.setDisable(true);
    }
    // Handles playing and pausing when button is pressed
    private void togglePlayPause() {
        if (mediaPlayer != null) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                playPauseButton.setText("Play");
            } else {
                mediaPlayer.play();
                playPauseButton.setText("Pause");
            }
        }
    }
    // Handles restarting track when button is pressed
    private void restart() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.ZERO);
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                mediaPlayer.play();
                playPauseButton.setText("Pause");
            }
        }
    }
    // Handles changing background when button is pressed 
    private void changeBackground() {
        Group newBackground;
        StackPane root = (StackPane) controlBox.getParent();
        // Switches to one of the backgrounds in the MediaPlayerBackgrounds class
        switch (backgroundIndex) {
            case 0: newBackground = MediaPlayerBackgrounds.createChillwaveBackground();
                    break;
            case 1: newBackground = MediaPlayerBackgrounds.createVaporwaveBackground();
                    break;
            case 2: newBackground = MediaPlayerBackgrounds.createSynthwaveBackground();
                    break;
            default: newBackground = MediaPlayerBackgrounds.createSynthwaveBackground();
        }

        root.getChildren().removeIf(node -> node instanceof Group);
        root.getChildren().add(0, newBackground);
        backgroundIndex = (backgroundIndex + 1) % 3;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
