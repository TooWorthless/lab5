package com.malikov;

import com.malikov.bank.Bank;
import com.malikov.bankAccount.BankAccount;
import com.malikov.exceptions.AccountNotFoundException;
import com.malikov.exceptions.InsufficientFundsException;
import com.malikov.exceptions.NegativeAmountException;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        BankAccount account1 = bank.createAccount("John Doe", 1000);
        try {
            account1.deposit(500);
            account1.withdraw(200);
            System.out.println(account1.getAccountSummary());
        } catch (NegativeAmountException | InsufficientFundsException e) {
            e.printStackTrace();
        }

        try {
            BankAccount account2 = bank.findAccount(2);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        BankAccount account3 = bank.createAccount("Jane Doe", 500);
        try {
            account3.withdraw(1000);
        } catch (InsufficientFundsException | NegativeAmountException e) {
            System.out.println(e.getMessage());
        }

        try {
            account3.deposit(-200);
        } catch (NegativeAmountException e) {
            System.out.println(e.getMessage());
        }

        try {
            BankAccount account4 = bank.createAccount("Alice", 800);
            BankAccount account5 = bank.createAccount("Bob", 1200);

            bank.transferMoney(account4.getAccountNumber(), account5.getAccountNumber(), 300);
            
            System.out.println(account4.getAccountSummary());
            System.out.println(account5.getAccountSummary());
        } catch (AccountNotFoundException | InsufficientFundsException | NegativeAmountException e) {
            e.printStackTrace();
        }
    }
}