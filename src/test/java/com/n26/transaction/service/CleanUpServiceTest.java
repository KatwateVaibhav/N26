package com.n26.transaction.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.n26.transaction.entity.Transaction;
import com.n26.transaction.repository.TransactionRepository;
import com.n26.transaction.service.impl.CleanUpService;
import com.n26.transaction.service.impl.DateUtil;

public class CleanUpServiceTest {
    private CleanUpService cleanUpService;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository();
        cleanUpService = new CleanUpService(transactionRepository, new DateUtil());
    }

    @Test
    public void deleteOldData() {
        Transaction transaction = new Transaction();
        UUID id = transaction.getId();
        transaction.setAmount(BigDecimal.ONE);
        transaction.setTimestamp(Date.from(Instant.now().minusSeconds(DateUtil.SECONDS_TRANSACTION_BECOME_OLD + 1)));
        transactionRepository.save(transaction);

        assertTrue(transactionRepository.findById(id).isPresent());

        cleanUpService.deleteOldData();

        assertFalse("Old transaction must be delete", transactionRepository.findById(id).isPresent());
    }
}