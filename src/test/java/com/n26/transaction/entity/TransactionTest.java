package com.n26.transaction.entity;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TransactionTest {

    @Test
    public void createDefault() {
        Transaction transaction = new Transaction();
        assertNotNull(transaction.getId());
    }

}