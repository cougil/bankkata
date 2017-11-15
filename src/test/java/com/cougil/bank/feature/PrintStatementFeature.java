package com.cougil.bank.feature;

import com.cougil.bank.Account;
import com.cougil.bank.Console;
import com.cougil.bank.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock
    private Console console;
    private Account account;

    @Before
    public void setUp() {
        TransactionRepository transactionRepository = new TransactionRepository();
        account = new Account(transactionRepository);
    }

    @Test
    public void print_statement_containing_all_transactions() {

        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = Mockito.inOrder(console);

        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");

    }
}
