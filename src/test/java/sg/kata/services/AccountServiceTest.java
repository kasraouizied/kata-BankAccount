package sg.kata.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import sg.kata.entity.Account;
import sg.kata.entity.Customer;
import sg.kata.entity.Operation;
import sg.kata.entity.OperationType;
import sg.kata.exception.AmountMustBePositiveValueException;
import sg.kata.exception.AmountNotAvailableException;
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
        Account accountWithNewDeposit = accountService.depositAmount("100001", 10);
        assertThat(accountWithNewDeposit).isEqualToIgnoringGivenFields(updatedAccount, "operations");
    }
    
    
    @Test
    public void account_retreiveAmount_function() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	Account updatedAccount = new Account("100001", 590, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
        Account accountWithNewDeposit = accountService.retreiveAmount("100001", 10);
        assertThat(accountWithNewDeposit).isEqualToIgnoringGivenFields(updatedAccount, "operations");
    }
    
    
    @Test
    public void account_deposit_negative_amount_function() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
    	
    	assertThrows(AmountMustBePositiveValueException.class, () -> {
    		   accountService.retreiveAmount("100001", -100);
		    });
    }
    
    @Test
    public void account_retreive_null_amount_function() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	Account updatedAccount = new Account("100001", 600, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
        Account accountWithNewDeposit = accountService.retreiveAmount("100001", 0);
        assertThat(accountWithNewDeposit).isEqualToIgnoringGivenFields(updatedAccount, "operations");
    }
    
    @Test
    public void account_retreive_negative_amount_function() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
    	
        assertThrows(AmountMustBePositiveValueException.class, () -> {
    		   accountService.retreiveAmount("100001", -200);
		    });
    }
    
    @Test
    public void account_retreive_not_available_amount_function() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
    	
    	AmountNotAvailableException exception = assertThrows(AmountNotAvailableException.class, () -> {
    		   accountService.retreiveAmount("100001", 700);
		    });
		 assertTrue(exception.getMessage().contains("Amount not available"));
    }
    
    
    @Test
    public void account_retreive_all_operation_function() throws Exception {
    	Customer customer = new Customer("nom", "prenom");
    	Account initialAccount = new Account("100001", 600, customer);
    	doReturn(initialAccount).when(accountRepository).getAccount("100001");
    	
        accountService.depositAmount("100001", 10);
        accountService.retreiveAllAmount("100001");
        accountService.depositAmount("100001", 500);
        accountService.retreiveAmount("100001", 50);
        List<Operation> operations = accountService.returnAllOperation("100001");
        
        Operation operation1 = operations.get(0);
        assertEquals(operation1.getAmount(), 10);
        assertEquals(operation1.getBalance(), 610);
        assertEquals(operation1.getOperationType(), OperationType.DEPOSIT);
        
        Operation operation2 = operations.get(1);
        assertEquals(operation2.getAmount(), 610);
        assertEquals(operation2.getBalance(), 0);
        assertEquals(operation2.getOperationType(), OperationType.WITHDRAWL);
        
        
        Operation operation3 = operations.get(2);
        assertEquals(operation3.getAmount(), 500);
        assertEquals(operation3.getBalance(), 500);
        assertEquals(operation3.getOperationType(), OperationType.DEPOSIT);
        
        
        Operation operation4 = operations.get(3);
        assertEquals(operation4.getAmount(), 50);
        assertEquals(operation4.getBalance(), 450);
        assertEquals(operation4.getOperationType(), OperationType.WITHDRAWL);
        

    }
    
}
