package com.techcasita.lambda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrimeTest {

    @Test
    public void check() {
        assertEquals(1, Prime.check(7).getDivisor());
        assertEquals(3, Prime.check(9).getDivisor());
        assertEquals(7, Prime.check(49).getDivisor());
    }

    @Test
    public void handleRequest() throws Exception {
        String json_input = "{\"number\" : 17 }";

        PrimeRequest pReq = new ObjectMapper().readValue("{\"number\": 17}", PrimeRequest.class);
        PrimeResponse pResp = new Prime().handleRequest(pReq, null);

        assertEquals(1, pResp.getDivisor());
        assertTrue(pResp.toString().startsWith("Yes"));

        String json = new ObjectMapper().writeValueAsString(pResp);
        assertTrue(json.contains("\"answer\":\"Yes, 17 is a prime number, divisible only by itself and 1\""));
    }
}