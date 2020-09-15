package sg.kata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.kata.entity.Account;
import sg.kata.exception.AccountNotFoundException;
import sg.kata.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account depositAmount(String accountNumber, long amount) throws Exception {
		synchronized (accountNumber) {
			Account account = accountRepository.getAccount(accountNumber);
			if (account == null) {
				throw new AccountNotFoundException("Account don't exist");
			}
			account.depositInAccount(amount);
			return account;
		}
	}

	public Account retreiveAmount(String accountNumber, long amount) throws Exception {
		synchronized (accountNumber) {
			Account account = accountRepository.getAccount(accountNumber);
			if (account == null) {
				throw new AccountNotFoundException("Account don't exist");
			}
			account.retreiveFromAccount(amount);
			return account;
		}
	}

	public Account retreiveAllAmount(String accountNumber) throws Exception {
		synchronized (accountNumber) {
			Account account = accountRepository.getAccount(accountNumber);
			if (account == null) {
				throw new AccountNotFoundException("Account don't exist");
			}
			account.retreiveFromAccount(account.getSolde());
			return account;
		}
	}
}
