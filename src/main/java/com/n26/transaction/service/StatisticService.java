package com.n26.transaction.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.n26.transaction.entity.Statistic;
import com.n26.transaction.entity.Transaction;

/**
 * Service for create statistic
 */

public interface StatisticService {
	
public Statistic generateStatisticLastMinute(@NotNull Collection<Transaction> transactions);
}
