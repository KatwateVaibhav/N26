package com.n26.transaction.service.impl;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.transaction.entity.Transaction;
import com.n26.transaction.repository.TransactionRepository;
import com.n26.transaction.service.TransactionService;
@Service
public class TransactionServiceImpl implements TransactionService {


	@Autowired
    private TransactionRepository transactionRepository;
	
	@Override
	public void save(@Valid @NotNull Transaction transaction) {
		transactionRepository.save(transaction);
		
	}

	@Override
	public void deleteAll() {
		transactionRepository.deleteAll();
		
	}

	@Override
	public @NotNull Collection<Transaction> findAll() {
		return transactionRepository.findAll();
	}

}
