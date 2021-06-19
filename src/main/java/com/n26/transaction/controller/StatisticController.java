package com.n26.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.transaction.entity.Statistic;
import com.n26.transaction.service.StatisticService;
import com.n26.transaction.service.TransactionService;
import com.n26.transaction.service.impl.DateUtil;


@RestController
@RequestMapping(path = "/statistics")
public class StatisticController {
	@Autowired
    private TransactionService transactionService;
	
	@Autowired
    private  StatisticService statisticService;


    /**
     * GET /statistics
     * This endpoint returns the statistics computed on the transactions within the last {@link DateUtil#SECONDS_TRANSACTION_BECOME_OLD} seconds.
     *
     * @return
     * {
     *      "sum": "1000.00",
     *      "avg": "100.53",
     *      "max": "200000.49",
     *      "min": "50.23",
     *      "count": 10
     * }
     */
    @GetMapping
    public Statistic statisticsLastMinute() {
        return statisticService.generateStatisticLastMinute(transactionService.findAll());
    }
}
