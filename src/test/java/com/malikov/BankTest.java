package com.malikov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.malikov.bank.Bank;
import com.malikov.bankAccount.BankAccount;
import com.malikov.exceptions.AccountNotFoundException;
import com.malikov.exceptions.InsufficientFundsException;
import com.malikov.exceptions.NegativeAmountException;

class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    public void testCreateAccount() throws NegativeAmountException {
        BankAccount account = bank.createAccount("John", 1000);
        assertNotNull(account);
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testNegativeAmountExceptionOnCreateAccount() {
        assertThrows(NegativeAmountException.class, () -> bank.createAccount("John", -1000));
    }

    @Test
    public void testFindAccount() throws NegativeAmountException, AccountNotFoundException {
        BankAccount createdAccount = bank.createAccount("John", 1000);
        int accountNumber = createdAccount.getAccountNumber();
        BankAccount foundAccount = bank.findAccount(accountNumber);
        assertEquals(createdAccount, foundAccount);
    }

    @Test
    public void testAccountNotFoundException() {
        assertThrows(AccountNotFoundException.class, () -> bank.findAccount(-5));
    }

    @Test
    public void testTransferMoney() throws NegativeAmountException, AccountNotFoundException, InsufficientFundsException {
        BankAccount account1 = bank.createAccount("John", 1000);
        BankAccount account2 = bank.createAccount("Doe", 500);

        bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 500);

        assertEquals(500, account1.getBalance());
        assertEquals(1000, account2.getBalance());
    }

    @Test
    public void testInsufficientFundsExceptionOnTransferMoney() throws NegativeAmountException {
        BankAccount account1 = bank.createAccount("John", 100);
        BankAccount account2 = bank.createAccount("Doe", 500);

        assertThrows(InsufficientFundsException.class, () -> bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), 5000));
    }

    @Test
    public void testNegativeAmountExceptionOnTransferMoney() throws NegativeAmountException {
        BankAccount account1 = bank.createAccount("John", 1000);
        BankAccount account2 = bank.createAccount("Doe", 500);

        assertThrows(NegativeAmountException.class, () -> bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), -500));
    }
}
