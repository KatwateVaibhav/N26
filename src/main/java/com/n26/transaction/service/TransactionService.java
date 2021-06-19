package com.n26.transaction.service;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.n26.transaction.entity.Transaction;


public interface TransactionService {

	void save(@Valid @NotNull Transaction transaction);

	void deleteAll();

	@NotNull
	Collection<Transaction> findAll();

}
