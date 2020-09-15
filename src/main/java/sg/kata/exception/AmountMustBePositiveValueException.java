package sg.kata.exception;

public class AmountMustBePositiveValueException extends RuntimeException {

    private String message;

    public AmountMustBePositiveValueException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
		return message;
	}
}
