package sg.kata.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import sg.kata.entity.Account;
import sg.kata.exception.AccountNotFoundException;

@Service
public class AccountRepository {

	Map<String, Account> accounts;
	
	public AccountRepository() {
		accounts = new HashMap<String, Account>();
	}
	public void addToAccount(Account account) {
		accounts.put(account.getNumAccount(), account);
	}
		
	public Account getAccount(String accountNumber) {
		if (! accounts.containsKey(accountNumber))
			throw new AccountNotFoundException("Account don't exist");
		
		return accounts.get(accountNumber);
	}
}
