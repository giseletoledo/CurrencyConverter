package br.com.oracleone;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyConversion {
    private final String fromCurrency;
    private final String toCurrency;
    private final double conversionRate;
    private final LocalDateTime timestamp;

    public CurrencyConversion(String fromCurrency, String toCurrency, double conversionRate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionRate = conversionRate;
        this.timestamp = LocalDateTime.now();
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Conversão de 1 %s para %s: %.2f - %s",
                fromCurrency, toCurrency, conversionRate,
                timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}

