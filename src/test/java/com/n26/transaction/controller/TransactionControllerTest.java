package com.n26.transaction.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.transaction.service.impl.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class TransactionControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private String url;

    @Before
    public void setUp() {
        url = "http://localhost:" + port + "/transactions";
    }

    @Test
    public void createTransactionJSONInvalid() throws JsonProcessingException {
        Map<String, Object> json = new HashMap<>();
        json.put("amount", "1");

        assertEquals(400, sendPost(json).getStatusCodeValue());
    }

    @Test
    public void createTransactionJSONParse() throws JsonProcessingException {
        Map<String, Object> json = new HashMap<>();
        json.put("amount", "1");
        json.put("timestamp", "2018-");

        assertEquals(422, sendPost(json).getStatusCodeValue());
    }

    @Test
    public void createTransaction() throws JsonProcessingException {
        Map<String, Object> json = new HashMap<>();
        json.put("amount", "1");
        json.put("timestamp", Instant.now().toString());

        assertEquals(201, sendPost(json).getStatusCodeValue());
    }

    @Test
    public void createTransactionOld() throws JsonProcessingException {
        Map<String, Object> json = new HashMap<>();
        json.put("amount", "1");
        json.put("timestamp", Instant.now().minusSeconds(DateUtil.SECONDS_TRANSACTION_BECOME_OLD + 1).toString());

        assertEquals(204, sendPost(json).getStatusCodeValue());
    }

    @Test
    public void createTransactionFuture() throws JsonProcessingException {
        Map<String, Object> json = new HashMap<>();
        json.put("amount", "1");
        json.put("timestamp", Instant.now().plusSeconds(30).toString());

        assertEquals(422, sendPost(json).getStatusCodeValue());
    }

    @Test
    public void createTransactionGetNotSupported() {
         assertEquals(405, restTemplate.getForEntity(url, String.class).getStatusCodeValue());
    }

    private ResponseEntity<String> sendPost(Map<String, Object> json) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonString = new ObjectMapper().writeValueAsString(json);
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

        return restTemplate.postForEntity(url, entity, String.class);
    }
}