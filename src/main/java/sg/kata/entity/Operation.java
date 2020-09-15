package sg.kata.entity;
import java.time.LocalDate;


public class Operation {

	private final OperationType operationType;
    private final LocalDate dateOperation;
    private final long amount;
    private final long balance;
    
	public OperationType getOperationType() {
		return operationType;
	}

	public LocalDate getDateOperation() {
		return dateOperation;
	}

	public long getAmount() {
		return amount;
	}

	public long getBalance() {
		return balance;
	}

	public Operation(OperationType operationType, LocalDate dateOperation, long amount, long balance) {
		super();
		this.operationType = operationType;
		this.dateOperation = dateOperation;
		this.amount = amount;
		this.balance = balance;
	}
       
}
