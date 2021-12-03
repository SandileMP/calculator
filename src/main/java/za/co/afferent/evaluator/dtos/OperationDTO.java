package za.co.afferent.evaluator.dtos;

public class OperationDTO {
    private int priority;
    private char operator;
    private int index;
    private String key;
    private String value;

    public OperationDTO(int priority, char operator) {
        this.priority = priority;
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
