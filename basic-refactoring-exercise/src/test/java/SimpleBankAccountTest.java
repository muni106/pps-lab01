import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int INITIAL_BALANCE = 0;
    public static final int HOLDER_ID = 1;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", HOLDER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void initialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void deposit() {
        int amount = 100;
        int expectedBalance = 100;
        bankAccount.deposit(accountHolder.getId(), amount);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void wrongDeposit() {
        int amount = 100;
        int wrongAmount = 50;
        int depositerID = 2;
        int expectedBalance = 100;
        bankAccount.deposit(accountHolder.getId(), amount);
        bankAccount.deposit(depositerID, wrongAmount);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void withdraw() {
        int depositAmount = 100;
        int withdrawalAmount = 70;
        int expectedBalance = 30;
        bankAccount.deposit(accountHolder.getId(), depositAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawalAmount);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void wrongWithdraw() {
        int depositAmount = 100;
        int withdrawerID = 2;
        int withdrawalAmount = 70;
        int wrongBalance = 100;
        bankAccount.deposit(accountHolder.getId(), depositAmount);
        bankAccount.withdraw(withdrawerID, withdrawalAmount);
        assertEquals(wrongBalance, bankAccount.getBalance());
    }
}
