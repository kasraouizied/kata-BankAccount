package sg.kata.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
			assertThat(account.getSolde() == 660).isTrue();
		}
    

}
