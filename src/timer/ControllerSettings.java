package timer;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sample.Controller;

import java.time.LocalTime;

public class ControllerSettings {

    private ControllerTimer rootWindow;

    public ComboBox<String> comboBoxEndHour;
    public ComboBox<String> comboBoxEndMin;
    public ComboBox<String> comboBoxEndSec;
    public ComboBox<String> comboBox1pHour;
    public ComboBox<String> comboBox1pMin;
    public ComboBox<String> comboBox1pSec;
    public ComboBox<String> comboBox2pHour;
    public ComboBox<String> comboBox2pMin;
    public ComboBox<String> comboBox2pSec;
    public ComboBox<String> comboBox3pHour;
    public ComboBox<String> comboBox3pMin;
    public ComboBox<String> comboBox3pSec;

    public void setMainWindow(ControllerTimer rootWindow){
        this.rootWindow = rootWindow;
    }

    public void setComboBoxes(){
        // ustawienie wartości
        String[] tableHours =  new String[24];
        for (int i = 0; i < 24; i++) {
            tableHours[i] = String.valueOf(i);
        }
        comboBoxEndHour.getItems().addAll(tableHours);
        comboBoxEndHour.setValue(String.valueOf(rootWindow.localTimeEnd.getHour()));

        comboBox1pHour.getItems().addAll(tableHours);
        comboBox1pHour.setValue(String.valueOf(rootWindow.localTimeFirstBreak.getHour()));

        comboBox2pHour.getItems().addAll(tableHours);
        comboBox2pHour.setValue(String.valueOf(rootWindow.localTimeSecondBreak.getHour()));

        comboBox3pHour.getItems().addAll(tableHours);
        comboBox3pHour.setValue(String.valueOf(rootWindow.localTimeThirdBreak.getHour()));

        String[] tableMin =  new String[60];
        for (int i = 0; i < 60; i++) {
            tableMin[i] = String.valueOf(i);
        }
        comboBoxEndMin.getItems().addAll(tableMin);
        comboBoxEndMin.setValue(String.valueOf(rootWindow.localTimeEnd.getMinute()));
        comboBoxEndSec.getItems().addAll(tableMin);
        comboBoxEndSec.setValue(String.valueOf(rootWindow.localTimeEnd.getSecond()));

        comboBox1pMin.getItems().addAll(tableMin);
        comboBox1pMin.setValue(String.valueOf(rootWindow.localTimeFirstBreak.getMinute()));
        comboBox1pSec.getItems().addAll(tableMin);
        comboBox1pSec.setValue(String.valueOf(rootWindow.localTimeFirstBreak.getSecond()));

        comboBox2pMin.getItems().addAll(tableMin);
        comboBox2pMin.setValue(String.valueOf(rootWindow.localTimeSecondBreak.getMinute()));
        comboBox2pSec.getItems().addAll(tableMin);
        comboBox2pSec.setValue(String.valueOf(rootWindow.localTimeSecondBreak.getSecond()));

        comboBox3pMin.getItems().addAll(tableMin);
        comboBox3pMin.setValue(String.valueOf(rootWindow.localTimeThirdBreak.getMinute()));
        comboBox3pSec.getItems().addAll(tableMin);
        comboBox3pSec.setValue(String.valueOf(rootWindow.localTimeThirdBreak.getSecond()));

    }
    public void buttonOkClicked(ActionEvent event) {
        rootWindow.localTimeEnd = LocalTime.of(Integer.valueOf(comboBoxEndHour.getValue()),
                Integer.valueOf(comboBoxEndMin.getValue()),
                Integer.valueOf(comboBoxEndSec.getValue()));
        rootWindow.localTimeFirstBreak = LocalTime.of(Integer.valueOf(comboBox1pHour.getValue()),
                Integer.valueOf(comboBox1pMin.getValue()),
                Integer.valueOf(comboBox1pSec.getValue()));
        rootWindow.localTimeSecondBreak = LocalTime.of(Integer.valueOf(comboBox2pHour.getValue()),
                Integer.valueOf(comboBox2pMin.getValue()),
                Integer.valueOf(comboBox2pSec.getValue()));
        rootWindow.localTimeThirdBreak = LocalTime.of(Integer.valueOf(comboBox3pHour.getValue()),
                Integer.valueOf(comboBox3pMin.getValue()),
                Integer.valueOf(comboBox3pSec.getValue()));

        ((Button)event.getSource()).getScene().getWindow().hide();
//        buttonClose.getScene().getWindow().hide();

    }
    public void buttonCancelClicked(ActionEvent event){
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
}
