package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {

    public static final String TITLE = "Kalkulator";
    public static final String VER = "1.2";

    public Calculator calculator = new Calculator();
    public TextField textField;
    public Button buttonDot;
    public Button buttonPlus;
    public Button buttonMinus;
    public Button buttonMul;
    public Button buttonDiv;
    public Button buttonPlusMinus;
    public VBox vBox;
    boolean shiftPressed = false;


    public void button9Clicked(){
        button0_9Clicked("9");
    }
    public void button8Clicked(){
        button0_9Clicked("8");
    }
    public void button7Clicked(){
        button0_9Clicked("7");
    }
    public void button6Clicked(){
        button0_9Clicked("6");
    }
    public void button5Clicked(){
        button0_9Clicked("5");
    }
    public void button4Clicked(){
        button0_9Clicked("4");
    }
    public void button3Clicked(){
        button0_9Clicked("3");
    }
    public void button2Clicked(){
        button0_9Clicked("2");
    }
    public void button1Clicked(){
        button0_9Clicked("1");
    }
    public void button0Clicked(){
        button0_9Clicked("0");
    }
    public void buttonDotClicked(){
        if(calculator.calculate){
            buttonAcClicked();
            return;
        }
        if(textField.getLength() == 0){
            textField.appendText("0");
        }

        if(calculator.isNumber1Ok() && calculator.getOperation() != null){
            String s = calculator.getNumber1String() + calculator.operation.getSign();
            if(textField.getLength() == s.length()){
                textField.appendText("0");
            }
        }
        if(textField.getText().charAt(textField.getLength()-1) == ')'){
            String s1 = textField.getText().substring(0, textField.getLength()-1);
            textField.setText(s1+".)");
            return;
        }

        textField.appendText(".");
        buttonDot.setDisable(true);
    }
    public void buttonDelClicked(){
        if(calculator.calculate){
            buttonAcClicked();
            return;
        }
        if(textField.getText().length() >0){
            //
            char ch = textField.getText().charAt(textField.getText().length()-1);
            if( ch == '.'){
                buttonDot.setDisable(false);
            }
            if( ch == ')'){
                // skasowanie - i ()
                String s = calculator.getNumber1String()+calculator.getOperation().getSign();
                String n2 = textField.getText().substring(s.length());
                textField.setText(s+n2.substring(2,n2.length()-1));
                return;
            }
            if(calculator.isOperation(String.valueOf(ch))){
                disableAllOperationsButtons(false);
                if(calculator.getNumber1String().indexOf('.') == -1){
                    buttonDot.setDisable(false);
                }else {
                    buttonDot.setDisable(true);
                }
                calculator.reset();
            }
            textField.setText(textField.getText().substring(0,textField.getText().length() - 1));
        }
    }
    public void buttonAcClicked(){
        textField.setText("");
        buttonDot.setDisable(false);
        buttonPlusMinus.setDisable(false);
        calculator.reset();
        disableAllOperationsButtons(false);
    }
    public void buttonPlusMinusClicked(){
        if(calculator.calculate){
            buttonAcClicked();
            return;
        }

        if(!calculator.isNumber1Ok() && textField.getLength() > 0){
            if(textField.getText().charAt(0) == '-'){
                textField.setText( textField.getText().substring(1, textField.getLength()));
            }else{
                textField.setText("-" + textField.getText());
            }
            return;
        }

        if(calculator.isNumber1Ok() ){
            String s = calculator.getNumber1String() + calculator.getOperation().getSign();
            int l = s.length();
            if(textField.getLength() > l){
                String n2 = textField.getText().substring(l);
                // czy jest minus
                if(n2.charAt(0) == '('){
                    // kasowanie nawiasu
                    textField.setText(s+n2.substring(2,n2.length()-1));
                }else{
                    // dodanie nawiasu z -
                    textField.setText(s+"(-"+n2+")");
                }
            }
        }
    }
    public void buttonPlusClicked(){
        if(!calculator.isNumber1Ok() && textField.getLength() > 0 ){
            buttonOperationClicked(OperationType.ADDITION);
        }
    }
    public void buttonMinusClicked(){
        if(!calculator.isNumber1Ok() && textField.getLength() > 0 ){
            buttonOperationClicked(OperationType.SUBTRACTION);
        }
    }
    public void buttonMulClicked(){
        if(!calculator.isNumber1Ok() && textField.getLength() > 0 ){
            buttonOperationClicked(OperationType.MULTIPLICATION);
        }
    }
    public void buttonDivClicked(){
        if(!calculator.isNumber1Ok() && textField.getLength() > 0 ){
            buttonOperationClicked(OperationType.DIVISION);
        }
    }
    public void buttonCalculateClicked(){
        if(calculator.calculate){
            buttonAcClicked();
            return;
        }
        if(calculator.isNumber1Ok() && calculator.getOperation() != null){

            String s = calculator.number1String + calculator.getOperation().getSign();
//            System.out.println(" pierwsza liczba  + op STRING : " + s);
//            System.out.println(" długość tego str : " + s.length());
            if(calculator.isNumber1Ok() && textField.getLength() > s.length()){
//                System.out.println("OBLICZAMY");
//                System.out.println(" text edit : " + textField.getText());
//                System.out.println(" pierwsza liczba : " + calculator.number1String);
                String n2 = textField.getText().substring(s.length());
//                System.out.println(" druga  liczba przed sprawdzeniem () : " + n2);
                if(n2.charAt(0) == '('){
                    //usuwamy nawiasy
                    n2 = n2.substring(1,n2.length()-1);
                }
//                System.out.println(" druga  liczba po sprawdzeniu() : " + n2);
                calculator.setNumber2String(n2);
                calculator.setNumber2(Double.parseDouble(n2));
                textField.appendText(" = "+ String.valueOf(calculator.calculate()));
            }
        }
    }

    private void buttonOperationClicked(OperationType operation){
        if(calculator.calculate){
            buttonAcClicked();
            return;
        }
            calculator.setNumber1String(textField.getText());
            calculator.setNumber1(Double.parseDouble(textField.getText()));
            calculator.setNumber1Ok(true);
            textField.appendText(operation.getSign());
            calculator.setOperation(operation);
            disableAllOperationsButtons(true);
            buttonDot.setDisable(false);
//            System.out.println(" first number : " + calculator.getNumber1());
//            System.out.println(" number 1 ok : " + calculator.isNumber1Ok());
//            System.out.println(" operation : " + calculator.getOperation().getName());

    }
    private void button0_9Clicked(String string){
        if(calculator.calculate){
            buttonAcClicked();
            return;
        }
        if(!calculator.isNumber1Ok()){
            if(textField.getLength() == 0) {
                textField.appendText(string);
                return;
            }
            if(textField.getLength() == 1 && textField.getText().charAt(0) == '-'){
                textField.appendText(string);
                return;
            }
            if(textField.getLength() == 1 && textField.getText().charAt(0) == '0'){
                textField.setText(string);
                return;
            }
            if(textField.getLength() == 2 && textField.getText().charAt(0) == '-' && textField.getText().charAt(1) == '0'){
                textField.setText("-"+string);
                return;
            }
            textField.appendText(string);
        }else {
            // druga liczba
            String s = calculator.getNumber1String()+calculator.getOperation().getSign();
            int len = s.length();
            if(textField.getLength() == len){
                textField.appendText(string);
                return;
            }
            if(textField.getLength() == len+1 && textField.getText().charAt(len) == '0'){
                textField.setText(s + string);
                return;
            }
            if(textField.getText().charAt(textField.getLength()-1) == ')'){
                String s1 = textField.getText().substring(0, textField.getLength()-1);
                textField.setText(s1+string+')');
                return;
            }
            textField.appendText(string);
        }
    }
    public void keyPressed(KeyEvent key){
//        System.out.println("naciśnięto klawisz: "+ key.getCode());
        switch (key.getCode()){
            case SHIFT: {
                shiftPressed = true;
                break;
            }
            case DIGIT0: {
                button0Clicked();
                break;
            }
            case DIGIT1: {
                button1Clicked();
                break;
            }
            case DIGIT2: {
                button2Clicked();
                break;
            }
            case DIGIT3: {
                button3Clicked();
                break;
            }
            case DIGIT4: {
                button4Clicked();
                break;
            }
            case DIGIT5: {
                button5Clicked();
                break;
            }
            case DIGIT6: {
                button6Clicked();
                break;
            }
            case DIGIT7: {
                button7Clicked();
                break;
            }
            case DIGIT8: {
                if(shiftPressed){
                    buttonMulClicked();
                    shiftPressed = false;
                    break;
                }else{
                    button8Clicked();
                    break;
                }
            }
            case DIGIT9: {
                button9Clicked();
                break;
            }
            case PERIOD: {
                buttonDotClicked();
                break;
            }
            case EQUALS: {
                buttonPlusClicked();
                break;
            }
            case MINUS: {
                buttonMinusClicked();
                break;
            }
            case SLASH: {
                buttonDivClicked();
                break;
            }
            case ENTER: {
                buttonCalculateClicked();
                break;
            }
            case BACK_SPACE: {
                buttonDelClicked();
                break;
            }
            case DELETE: {
                buttonAcClicked();
                break;
            }
            case BACK_QUOTE: {
                buttonPlusMinusClicked();
                break;
            }

            default:{
                shiftPressed = false;
            }
        }
    }
    private void disableAllOperationsButtons(boolean disable){
        buttonPlus.setDisable(disable);
        buttonMinus.setDisable(disable);
        buttonMul.setDisable(disable);
        buttonDiv.setDisable(disable);

    }
    public void buttonAboutClicked(){
        AlertBox.display(TITLE, "Kalkulator w wersji " + VER+ "\n Autor : Jarosław Świdzicki");
    }

    public void buttonHelpClicked() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("helpBox.fxml"));
        VBox newWindow = (VBox) loader.load();
        ControllerHelpBox controller = loader.getController();
        controller.setMainWindow(this);
        Stage stage = new Stage();
        stage.setTitle("Help");
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initOwner(btnNext.getScene().getWindow());
        Scene scene = new Scene(newWindow);
        stage.setScene(scene);
        controller.helpBoxTextArea.setText(" Skróty klawiszowe do obsługi kalkulatora : " +
                "\n  - liczby od 0 - 9 - odpowiednie klawisze" +
                "\n  - działania -, +, / odpowiednie klawisze" +
                "\n  - mnożenie to shift+8" +
                "\n  - zmiana znaku(+/-) to klawisz ` lub ~" +
                "\n  - DEL to Backspace" +
                "\n  - AC to Delete" +
                "\n  - = to Enter");

        stage.showAndWait();



    }
}
