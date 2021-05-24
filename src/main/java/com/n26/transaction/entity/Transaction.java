package com.n26.transaction.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Transient
    private final UUID id = UUID.randomUUID();

    /**
     * Transaction amount; a string of arbitrary length that is parsable as a BigDecimal
     */
    @NotNull
    private BigDecimal amount;

    /**
     *  Transaction time in the ISO 8601 format YYYY-MM-DDThh:mm:ss.sssZ in the UTC timezone (this is not the current timestamp)
     */
    @NotNull
    @DateTimeFormat(pattern = "YYYY-MM-DDThh:mm:ss.sssZ")
    private Date timestamp;
}
