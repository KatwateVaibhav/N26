package com.n26.transaction.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.n26.transaction.serializer.TxnSerializer;
import com.n26.transaction.service.impl.DateUtil;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Statistic {
    /**
     *  The total sum of transaction value in the last {@link DateUtil#SECONDS_TRANSACTION_BECOME_OLD} seconds
     */
    @JsonSerialize(using = TxnSerializer.class)
    private BigDecimal sum = BigDecimal.ZERO;

    /**
     *  The average amount of transaction value in the last {@link DateUtil#SECONDS_TRANSACTION_BECOME_OLD} seconds
     */
    @JsonSerialize(using = TxnSerializer.class)
    private BigDecimal avg = BigDecimal.ZERO;

    /**
     * Single highest transaction value in the last {@link DateUtil#SECONDS_TRANSACTION_BECOME_OLD} seconds
     */
    @JsonSerialize(using = TxnSerializer.class)
    private BigDecimal max = BigDecimal.ZERO;

    /**
     * Single lowest transaction value in the last {@link DateUtil#SECONDS_TRANSACTION_BECOME_OLD} seconds
     */
    @JsonSerialize(using = TxnSerializer.class)
    private BigDecimal min = BigDecimal.ZERO;

    /**
     * The total number of transactions that happened in the last {@link DateUtil#SECONDS_TRANSACTION_BECOME_OLD} seconds
     */
    private long count = 0;
}
