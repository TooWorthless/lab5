package com.malikov;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.malikov.bankAccount.BankAccount;
import com.malikov.exceptions.InsufficientFundsException;
import com.malikov.exceptions.NegativeAmountException;


public class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {

        account = new BankAccount(1, "John", 1000);
    }

    @Test
    public void testDeposit() throws NegativeAmountException {
        account.deposit(500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void testNegativeAmountExceptionOnDeposit() {
        assertThrows(NegativeAmountException.class, () -> account.deposit(-100));
    }

    @Test
    public void testWithdraw() throws NegativeAmountException, InsufficientFundsException {
        account.withdraw(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    public void testInsufficientFundsExceptionOnWithdraw() {
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(1500));
    }

    @Test
    public void testNegativeAmountExceptionOnWithdraw() {
        assertThrows(NegativeAmountException.class, () -> account.withdraw(-100));
    }
}
