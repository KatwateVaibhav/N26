package com.n26.transaction.entity;

import org.junit.Test;

import com.n26.transaction.entity.Transaction;

import static org.junit.Assert.assertNotNull;

public class TransactionTest {

    @Test
    public void createDefault() {
        Transaction transaction = new Transaction();
        assertNotNull(transaction.getId());
    }

}