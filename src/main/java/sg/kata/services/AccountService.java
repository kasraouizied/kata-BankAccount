package sg.kata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.kata.entity.Account;
import sg.kata.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account deposit(String accountNumber, long amount) throws Exception {
		synchronized (accountNumber) {
			Account account = accountRepository.getAccount(accountNumber);
			if (account == null) {
				throw new Exception("Account don't exist");
			}
			account.depositInAccount(amount);
			return account;
		}
	}

}
