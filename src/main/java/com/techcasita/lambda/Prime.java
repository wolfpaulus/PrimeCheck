/*
 * Prime, a very basic AWS Lambda Function
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Lambda request handlers implement AWS Lambda Function application logic,
 * using plain old java objects as input and output.
 * However, input and output is JSON encoded and AWS Lambda will perform (de-)serialization,
 * using a very old version of com.fasterxml.jackson
 */
public class Prime implements RequestHandler<PrimeRequest, PrimeResponse> {
    private static final Logger Log = LogManager.getLogger(Prime.class);

    /**
     * Check if the provided number is evenly divisible only by itself and one.
     *
     * @param n {@link Long}
     * @return {@link boolean} true, if the provided long is a prime number
     */
    static PrimeResponse check(final long n) {
        if (n < 2) {
            return new PrimeResponse();
        }
        if (n == 2 || n == 3) {
            return new PrimeResponse(n);
        }
        if (n % 2 == 0) {
            return new PrimeResponse(n, 2);
        }
        if (n % 3 == 0) {
            return new PrimeResponse(n, 3);
        }
        final long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0) {
                return new PrimeResponse(n, i - 1);
            }
            if (n % (i + 1) == 0) {
                return new PrimeResponse(n, i + 1);
            }

        }
        return new PrimeResponse(n);
    }

    /**
     * Handles a Lambda Function request
     *
     * @param input   {@link PrimeRequest} The Lambda Function input
     * @param context {@link Context} The Lambda execution environment context object.
     * @return {@link PrimeResponse} - The Lambda Function output
     */
    @Override
    public PrimeResponse handleRequest(final PrimeRequest input, final Context context) {
        Log.info("Request received:" + input);
        return Prime.check(input.getNumber());
    }
}
