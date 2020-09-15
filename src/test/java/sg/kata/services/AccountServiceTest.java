package sg.kata.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import sg.kata.entity.Account;
import sg.kata.entity.Customer;
import sg.kata.repository.AccountRepository;

@RunWith(SpringRunner.class)
public class AccountServiceTest {
		
    @Mock
    private AccountRepository accountRepository;

    @Spy
    @InjectMocks
    private AccountService accountService;

    
    @Test
    public void account_deposit() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	Account updatedAccount = new Account("100001", 610, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
        Account accountWithNewDeposit = accountService.deposit("100001", 10);
        assertThat(accountWithNewDeposit).isEqualToComparingFieldByField(updatedAccount);
    }
    
}
