package sample;

public class Calculator {
    double number1;
    String number1String;
    boolean number1Ok;
    double number2;
    String number2String;
    boolean number2Ok;
    OperationType operation;
    boolean calculate;

    public Calculator() {
        reset();
    }
    public void reset(){
        this.number1 = 0;
        this.number2 = 0;
        this.number1String = "";
        this.number2String = "";
        this.number1Ok = false;
        this.number2Ok = false;
        operation = null;
        calculate = false;
    }

    public double getNumber1() {
        return number1;
    }

    public String getNumber1String() {
        return number1String;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public void setNumber1String(String number1String) {
        this.number1String = number1String;
    }

    public boolean isNumber1Ok() {
        return number1Ok;
    }

    public void setNumber1Ok(boolean number1Ok) {
        this.number1Ok = number1Ok;
    }

    public double getNumber2() {
        return number2;
    }
    public String getNumber2String() {
        return number2String;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }
    public void setNumber2String(String number2String) {
        this.number2String = number2String;
    }

    public boolean isNumber2Ok() {
        return number2Ok;
    }

    public void setNumber2Ok(boolean number2Ok) {
        this.number2Ok = number2Ok;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
    public boolean isOperation(String string){
        for (OperationType op : OperationType.values()) {
            if(op.getSign().equalsIgnoreCase(string)){
                return true;
            }
        }
        return false;
    }
    public double calculate(){
        //double result  = 0;
        switch (operation){
            case ADDITION:{
                calculate = true;
                return this.number1 + this.number2;
            }
            case SUBTRACTION:{
                calculate = true;
                return  this.number1 - this.number2;
            }
            case MULTIPLICATION:{
                calculate = true;
                return this.number1 * this.number2;
            }
            case DIVISION:{
                calculate = true;
                return this.number1 / this.number2;
            }
        }
        return 0;
    }
}
