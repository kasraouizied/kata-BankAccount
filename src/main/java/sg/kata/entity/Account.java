package sg.kata.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sg.kata.exception.AmountMustBePositiveValueException;
import sg.kata.exception.AmountNotAvailableException;

public class Account {

    private final String numAccount;

    private long solde;
    
    private final Customer customer;
    
    private List<Operation> operations;
    
    public Account(String numAccount, long solde, Customer customer) {
    	this.numAccount = numAccount;
    	this.solde = solde;
    	this.customer = customer;
    	this.operations = new ArrayList<Operation>();
    }
    
	public synchronized void depositInAccount(long amount) {
		if (amount < 0) {
			throw new AmountMustBePositiveValueException("Amount must be positive value");
		}
		this.solde += amount;

		this.registerOperation(OperationType.DEPOSIT, amount);
	}
	
	public List<Operation> getOperation() {
		return operations;
	}

	public long getSolde() {
		return solde;
	}

	public String getNumAccount() {
		return numAccount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public synchronized void retreiveFromAccount(long amount) throws Exception {
		if (amount < 0) {
			throw new AmountMustBePositiveValueException("Amount must be positive value");
		}
		if (getSolde() < amount) {
			throw new AmountNotAvailableException("Amount not available");
		}
		this.solde -= amount;
		this.registerOperation(OperationType.WITHDRAWL, amount);
	}
	
	private void registerOperation(OperationType operationType, long amount) {
       operations.add(new Operation(operationType, LocalDate.now(), amount, solde));
	}
       
}
