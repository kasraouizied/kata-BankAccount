package sg.kata.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.kata.entity.Account;
import sg.kata.entity.Operation;
import sg.kata.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account depositAmount(String accountNumber, long amount) throws Exception {
		Account account = accountRepository.getAccount(accountNumber);
		account.depositInAccount(amount);
		return account;
	}

	public Account retreiveAmount(String accountNumber, long amount) throws Exception {
		synchronized (accountNumber) {
			Account account = accountRepository.getAccount(accountNumber);			
			account.retreiveFromAccount(amount);
			return account;
		}
	}

	public Account retreiveAllAmount(String accountNumber) throws Exception {
		Account account = accountRepository.getAccount(accountNumber);
		account.retreiveFromAccount(account.getSolde());
		return account;
	}
	
	public List<Operation> returnAllOperation(String accountNumber) throws Exception {
			Account account = accountRepository.getAccount(accountNumber);
			return account.getOperation();
	}
}
