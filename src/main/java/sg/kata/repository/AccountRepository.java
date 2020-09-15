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
	public void addToAccount(String accountNumber, Account account) {
		accounts.put(accountNumber, account);
	}
		
	public Account getAccount(String accountNumber) {
		Account account = accounts.get(accountNumber);
		if (account == null) {
			throw new AccountNotFoundException("Account don't exist");
		}
		return accounts.get(accountNumber);
	}
}
