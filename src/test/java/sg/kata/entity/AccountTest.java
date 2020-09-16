package sg.kata.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import sg.kata.exception.AmountNotAvailableException;

@RunWith(SpringRunner.class)
public class AccountTest {

	Account account;

	@Before
	public void init() {
		account = new Account("100002", 540, new Customer("nom", "prenom"));

	}

	@Test
	public void test_deposit_function() {
		account.depositInAccount(120);
		assertThat(account.getSolde()).isEqualTo(660);
	}

	@Test
	public void test_retreive_function() {
		try {
			account.retreiveFromAccount(30);
		} catch (Exception e) {

		}
		assertThat(account.getSolde()).isEqualTo(510);
	}

	@Test
	public void test_retreive_not_available_amount_function() {

		assertThrows(AmountNotAvailableException.class, () -> {
			account.retreiveFromAccount(800);
		});
		assertThat(account.getSolde()).isEqualTo(540);
	}

}
