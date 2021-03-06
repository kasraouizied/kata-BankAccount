package sg.kata.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sg.kata.entity.Account;
import sg.kata.entity.Customer;
import sg.kata.exception.AccountNotFoundException;

@RunWith(SpringRunner.class)
public class AccountRepositoryTest {

	private AccountRepository accountRepository;
	private Account expectedAccount1;
	private Account expectedAccount2;

	/* init bank accounts */
	@Before
	public void init() {
		accountRepository = new AccountRepository();
		expectedAccount1 = new Account("100001", 400, new Customer("prenom1", "nom1"));
		expectedAccount2 = new Account("100006", 340, new Customer("prenom6", "nom6"));
		accountRepository.addToAccount(expectedAccount1);
		accountRepository.addToAccount(new Account("100002", 150, new Customer("prenom2", "nom2")));
		accountRepository.addToAccount(new Account("100003", 110, new Customer("prenom3", "nom3")));
		accountRepository.addToAccount(new Account("100004", 120, new Customer("prenom4", "nom4")));
		accountRepository.addToAccount(new Account("100005", 110, new Customer("prenom5", "nom5")));
		accountRepository.addToAccount(expectedAccount2);

	}

	@Test
	public void fetch_exist_account1() {
		Account account = accountRepository.getAccount("100001");
		assertThat(account).isNotNull().isEqualToComparingFieldByField(expectedAccount1);

	}

	@Test
	public void fetch_exist_account6() {
		Account account = accountRepository.getAccount("100006");

		assertThat(account).isNotNull().isEqualToComparingFieldByField(expectedAccount2);

	}

	@Test
	public void fetch_not_exist_account() {
		assertThrows(AccountNotFoundException.class, () -> {
			accountRepository.getAccount("100007");
		});
	}

}
