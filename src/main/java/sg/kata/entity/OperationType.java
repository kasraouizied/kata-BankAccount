package sg.kata.entity;

public enum OperationType {
    DEPOSIT("deposit"),
    WITHDRAWL("retreive");

    private String value;

    OperationType(String value) {
        this.value = value;
    }
}
