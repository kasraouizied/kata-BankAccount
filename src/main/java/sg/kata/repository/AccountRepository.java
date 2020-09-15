package sg.kata.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import sg.kata.entity.Account;

@Service
public class AccountRepository {

	Map<String, Account> accounts;
	
	public AccountRepository() {
		accounts = new HashMap<String, Account>();
	}
	public void addAccount(String accountNumber, Account account) {
		accounts.put(accountNumber, account);
	}
	
	public Account getAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}
}
