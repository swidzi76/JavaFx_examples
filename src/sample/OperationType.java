package sample;

public enum OperationType {
    ADDITION("+", "dodawanie"),
    SUBTRACTION("-", "odejmowanie"),
    MULTIPLICATION("*", "mnożenie"),
    DIVISION("/", "dzielenie");

    String sign;
    String name;
    OperationType(String sign, String name) {
        this.sign = sign;
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }
}
