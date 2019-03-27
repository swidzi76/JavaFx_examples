package timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import sample.Controller;
import sample.ControllerHelpBox;

import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
public class TimerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("timer.fxml"));
        VBox vBox = loader.load();
        ControllerTimer controller = loader.getController();

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);

        primaryStage.setTitle(ControllerTimer.TITLE + " v. " + ControllerTimer.VER);
//        primaryStage.setScene(new Scene(root, 400,    400));
        primaryStage.setResizable(false);
        primaryStage.show();

//        controller.buttonClicked();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> controller.setLabels()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();






        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                timeline.stop();
                Platform.exit();
                System.exit(0);
            }
        });
//
    }
}
