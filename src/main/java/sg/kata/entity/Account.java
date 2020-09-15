package sg.kata.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import sg.kata.exception.AmountMustBePositiveValueException;
import sg.kata.exception.AmountNotAvailableException;

public class Account {

    private final String numAccount;

    private long solde;
    
    private final Customer customer;
    
    private ArrayList<Operation> operations;
    
    public Account(String numAccount, long solde, Customer customer) {
    	this.numAccount = numAccount;
    	this.solde = solde;
    	this.customer = customer;
    	this.operations = new ArrayList<Operation>();
    }
    
	public void depositInAccount(long amount) {
		synchronized (this) {
			if ( amount < 0 ) {
				throw new AmountMustBePositiveValueException("Amount must be positive value");
			}
			this.solde = this.solde + amount;
			this.registerOperation(OperationType.DEPOSIT, amount);
		}
	}
	
	public ArrayList<Operation> getOperation() {
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

	public void retreiveFromAccount(long amount) throws AmountNotAvailableException {
		synchronized (this) {
			if ( amount < 0 ) {
				throw new AmountMustBePositiveValueException("Amount must be positive value");
			}
			if (getSolde() < amount) {
				throw new AmountNotAvailableException("Amount not available");
			}
			this.solde = this.solde - amount;
			this.registerOperation(OperationType.WITHDRAWL, amount);
		}
	}
	
	private void registerOperation(OperationType operationType, long amount) {
       operations.add(new Operation(operationType, LocalDate.now(), amount, solde));
	}
       
}
