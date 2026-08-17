package com.microservice.transaction.Utility;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class GenerateTxnID {
	
	private static final AtomicInteger counter = new AtomicInteger(0);

    public static synchronized String generateTxnId() {

        LocalDateTime now = LocalDateTime.now();

        String year = String.format("%02d", now.getYear() % 100);
        String minute = String.format("%02d", now.getMinute());
        String second = String.format("%02d", now.getSecond());
        String milli = String.format("%03d", now.getNano() / 1_000_000);

        int count = counter.getAndIncrement() % 10;

        return year + minute + second + milli + count;
    }
}
