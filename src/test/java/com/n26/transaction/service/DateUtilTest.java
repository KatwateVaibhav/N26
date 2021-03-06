package com.n26.transaction.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.n26.transaction.entity.Transaction;
import com.n26.transaction.service.impl.DateUtil;

public class DateUtilTest {
    private DateUtil dateUtil;

    @Before
    public void setUp() {
        dateUtil = new DateUtil();
    }

    @Test
    public void isTransactionOlderMinute() {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(Date.from(Instant.now()));
        assertFalse("Transaction created now", dateUtil.isTransactionOld(transaction));

        transaction.setTimestamp(Date.from(Instant.now().minusSeconds(DateUtil.SECONDS_TRANSACTION_BECOME_OLD + 1)));
        assertTrue("Transaction is older one minute", dateUtil.isTransactionOld(transaction));
    }

    @Test
    public void isTransactionInFuture() {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(Date.from(Instant.now()));
        assertFalse("Transaction created now", dateUtil.isTransactionInFuture(transaction));

        transaction.setTimestamp(Date.from(Instant.now().plusSeconds(10)));
        assertTrue("Transaction is from future", dateUtil.isTransactionInFuture(transaction));

        transaction.setTimestamp(Date.from(Instant.now().minusSeconds(1)));
        assertFalse("Transaction from past", dateUtil.isTransactionInFuture(transaction));
    }

}