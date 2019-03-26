package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerHelpBox {

    @FXML
    public TextArea helpBoxTextArea;

    @FXML
    private Button buttonClose;

    private Controller rootWindow;

    public void setMainWindow(Controller rootWindow){
        this.rootWindow = rootWindow;
    }
    public void buttonCloseClicked(ActionEvent event){
        ((Button)event.getSource()).getScene().getWindow().hide();
//        buttonClose.getScene().getWindow().hide();
    }
}
