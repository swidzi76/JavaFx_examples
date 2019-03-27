package timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.TimerTask;

public class ControllerTimer {

    public Label label1;
    public Label labelTimeToEnd;
    public Label labelTimeToFirstBreak;
    public Label labelTimeToSecondBreak;
    public Label labelTimeToThirdBreak;
    public Label labelTimeToThirdBrakeDescription;
    public Label labelTimeToFirstBrakeDescription;
    public Label labelTimeToSecondBrakeDescription;
    public Label labelTimeToEndDescription;

    private static String  message1 = "";
    final static public String TITLE = "TimerApp";
    final static public String VER = "1.3";
    final static public String SETTINGS_FILE = "settings.txt";
    final static public String LABEL_1_BREAK = "do 1 przerwy";
    final static public String LABEL_2_BREAK = "do 2 przerwy";
    final static public String LABEL_3_BREAK = "do 3 przerwy";
    final static public String LABEL_END = "do końca zajęć";

    final static public String COLOR_BLACK = "#000000";
    final static public String COLOR_GREEN = "#00f56e";
    final static public String COLOR_RED = "#ff0000";

    public LocalTime localTimeEnd;
    public LocalTime localTimeFirstBreak;
    public LocalTime localTimeSecondBreak;
    public LocalTime localTimeThirdBreak;
    public ControllerTimer() {
//        labelTimeToEnd.setTextFill(Paint.valueOf(COLOR_GREEN));
    }

    @FXML
    public void buttonSettingsClicked(ActionEvent event) throws Exception {
        // pokazanie okna z ustawieniami - settings.fxml
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        VBox vBox = (VBox) loader.load();

        ControllerSettings controllerSettings = loader.getController();
        controllerSettings.setMainWindow(this);
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Settings ");
        stage.setResizable(false);
        controllerSettings.setComboBoxes(); // set comboBox
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }
    public void buttonDefaultSettingsClicked(ActionEvent event) throws IOException{
        defaultTime();
        saveTimesToFile();
    }

    public void setLabels(){
        // ustawienie czasu w label
        LocalDateTime localTime = LocalDateTime.now();

        label1.setText(timeToStr(localTime.getHour(), localTime.getMinute(), localTime.getSecond()));

//        localTimeEnd = LocalTime.of(16,00,00);
//        localTimeFirstBreak = LocalTime.of(10,30,00);
//        localTimeSecondBreak = LocalTime.of(12,30,00);
//        localTimeThirdBreak = LocalTime.of(14,30,00);
        // ustawienie opisu
        labelTimeToFirstBrakeDescription.setText(LABEL_1_BREAK+"("+timeToStr(localTimeFirstBreak.getHour(),
                localTimeFirstBreak.getMinute(),
                localTimeFirstBreak.getSecond())+")");
        labelTimeToSecondBrakeDescription.setText(LABEL_2_BREAK+"("+timeToStr(localTimeSecondBreak.getHour(),
                localTimeSecondBreak.getMinute(),
                localTimeSecondBreak.getSecond())+")");
        labelTimeToThirdBrakeDescription.setText(LABEL_3_BREAK+"("+timeToStr(localTimeThirdBreak.getHour(),
                localTimeThirdBreak.getMinute(),
                localTimeThirdBreak.getSecond())+")");
        labelTimeToEndDescription.setText(LABEL_END+"("+timeToStr(localTimeEnd.getHour(),
                localTimeEnd.getMinute(),
                localTimeEnd.getSecond())+")");
        // ustawienie czasu
        Duration duration = Duration.between(localTime.toLocalTime(), localTimeEnd);
        setLabel(labelTimeToEnd, duration);

        duration = Duration.between(localTime.toLocalTime(), localTimeFirstBreak);
        setLabel(labelTimeToFirstBreak, duration);

        duration = Duration.between(localTime.toLocalTime(), localTimeSecondBreak);
        setLabel(labelTimeToSecondBreak, duration);

        duration = Duration.between(localTime.toLocalTime(), localTimeThirdBreak);
        setLabel(labelTimeToThirdBreak, duration);

    }
    private void setLabel(Label label, Duration duration){
        int hour=0, min=0, sec=0;
        if(duration.getSeconds()>=0){
            int getSec = (int) duration.getSeconds() + 1;
            hour = getSec / 3600;
            min = (getSec - hour*3600) / 60;
            sec = getSec - hour *3600 - min * 60;
//            hour = (int) duration.toHours();
//            min = (int) (duration.toMinutes() - hour*60);
//            sec = (int) (duration.getSeconds()- hour*3600 - min*60 + 1);
            label.setTextFill(Paint.valueOf(COLOR_GREEN));
        }else{
            label.setTextFill(Paint.valueOf(COLOR_RED));
        }
        label.setText(timeToStr(hour, min, sec));

    }
    private String timeToStr(int hour, int min, int sec){
        String stringHour;
        if(hour < 10){
            stringHour = "0" + hour;
        }else{
            stringHour = "" + hour;
        }
        String stringMin;
        if(min < 10){
            stringMin = "0" + min;
        }else{
            stringMin = "" + min;
        }
        String stringSec;
        if(sec < 10){
            stringSec = "0" + sec;
        }else{
            stringSec = "" + sec;
        }
        return stringHour + " : " + stringMin + " : " + stringSec;

    }
    public void defaultTime(){
        localTimeEnd = LocalTime.of(16,00,00);
        localTimeFirstBreak = LocalTime.of(10,30,00);
        localTimeSecondBreak = LocalTime.of(12,30,00);
        localTimeThirdBreak = LocalTime.of(14,30,00);
    }
    public void saveTimesToFile() throws IOException{
        PrintWriter printWriter = new PrintWriter(new FileWriter(SETTINGS_FILE));
        printWriter.println(""+localTimeFirstBreak.getHour()+","+localTimeFirstBreak.getMinute()+","+localTimeFirstBreak.getSecond());
        printWriter.println(""+localTimeSecondBreak.getHour()+","+localTimeSecondBreak.getMinute()+","+localTimeSecondBreak.getSecond());
        printWriter.println(""+localTimeThirdBreak.getHour()+","+localTimeThirdBreak.getMinute()+","+localTimeThirdBreak.getSecond());
        printWriter.println(""+localTimeEnd.getHour()+","+localTimeEnd.getMinute()+","+localTimeEnd.getSecond());
        printWriter.close();
    }
    public void readTimesFromFile(){
        String s1;
        String[] as;
        int h,m,s;
        try (Scanner scanner = new Scanner(new File(SETTINGS_FILE))) {
            s1 = scanner.nextLine();
            as = s1.split(",");
            h = Integer.valueOf(as[0]);
            m = Integer.valueOf(as[1]);
            s = Integer.valueOf(as[2]);
            localTimeFirstBreak = LocalTime.of(h,m,s);

            s1 = scanner.nextLine();
            as = s1.split(",");
            h = Integer.valueOf(as[0]);
            m = Integer.valueOf(as[1]);
            s = Integer.valueOf(as[2]);
            localTimeSecondBreak = LocalTime.of(h,m,s);

            s1 = scanner.nextLine();
            as = s1.split(",");
            h = Integer.valueOf(as[0]);
            m = Integer.valueOf(as[1]);
            s = Integer.valueOf(as[2]);
            localTimeThirdBreak = LocalTime.of(h,m,s);

            s1 = scanner.nextLine();
            as = s1.split(",");
            h = Integer.valueOf(as[0]);
            m = Integer.valueOf(as[1]);
            s = Integer.valueOf(as[2]);
            localTimeEnd = LocalTime.of(h,m,s);


        } catch (IOException e) {
            System.out.println("IOException!");
        }

    }
}
