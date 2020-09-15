package sg.kata.entity;


public class Account {

    private final String numAccount;

    private long solde;
    
    private final Customer customer;
    
    public Account(String numAccount, long solde, Customer customer) {
    	this.numAccount = numAccount;
    	this.solde = solde;
    	this.customer = customer;
    }
    
    public void depositInAccount(long deposit) {
    	this.solde = this.solde + deposit;
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
    
    
   
}
