package timer;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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

    private static String  message1 = "";
    final static public String TITLE = "TimerApp";
    final static public String VER = "1.1";
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

    public void setLabels(){
        // ustawienie czasu w label
        LocalDateTime localTime = LocalDateTime.now();

        label1.setText(timeToStr(localTime.getHour(), localTime.getMinute(), localTime.getSecond()));

        localTimeEnd = LocalTime.of(16,00,00);
        localTimeFirstBreak = LocalTime.of(10,30,00);
        localTimeSecondBreak = LocalTime.of(12,30,00);
        localTimeThirdBreak = LocalTime.of(14,30,00);

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
            hour = (int) duration.toHours();
            min = (int) (duration.toMinutes() - hour*60);
            sec = (int) (duration.getSeconds()- hour*3600 - min*60 + 1);
        }else{
            label.setTextFill(Paint.valueOf(COLOR_RED));
        }
        label.setText(timeToStr(hour, min, sec));

    }
    public void buttonClicked(){
        this.label1.setText("abcdef");
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
}
