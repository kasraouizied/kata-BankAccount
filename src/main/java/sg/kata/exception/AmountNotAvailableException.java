package sg.kata.exception;

public class AmountNotAvailableException extends RuntimeException {

    private String message;

    public AmountNotAvailableException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
		return message;
	}
}
