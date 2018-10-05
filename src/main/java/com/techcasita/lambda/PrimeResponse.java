/*
 * PrimeResponse
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.lambda;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AWS Lambda is currently using a very old Jackson Lib that does not yet support annotation like:
 * JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
 * Therefore, JsonProperty must be declared and if private, getter methods needs to be implemented.
 */
@SuppressWarnings({"unused", "WeakerAccess", "FieldCanBeLocal"})
public class PrimeResponse {
    private static final Logger log = LogManager.getLogger(PrimeResponse.class);
    @JsonProperty
    private final String tag = "PrimeCheck P1";
    @JsonProperty
    private final String answer;
    @JsonProperty
    private long n;
    @JsonProperty
    private long d;


    public PrimeResponse(final long n, final long d) {
        this.n = n;
        this.d = d;
        answer = String.format("No, %d is not a prime number. It's divisible by %d", n, d);
    }

    public PrimeResponse(final long n) {
        log.info("Found a prime number " + n);
        this.n = n;
        this.d = 1;
        answer = String.format("Yes, %d is a prime number, divisible only by itself and 1", n);
    }

    public PrimeResponse() {
        answer = "A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.";
    }

    @Override
    public String toString() {
        return answer;
    }

    long getDivisor() {
        return d;
    }

    // getters for jackson serialization:

    public String getTag() {
        return tag;
    }

    public String getAnswer() {
        return answer;
    }

    public long getN() {
        return n;
    }

    public long getD() {
        return d;
    }
}
