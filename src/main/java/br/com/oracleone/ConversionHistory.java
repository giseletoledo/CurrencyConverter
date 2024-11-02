package br.com.oracleone;

import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private final List<CurrencyConversion> history;

    public ConversionHistory() {
        this.history = new ArrayList<>();
    }

    public void addConversion(Currency currency) {
        CurrencyConversion conversion = new CurrencyConversion(
                currency.base_code(), currency.target_code(), currency.conversion_rate());
        history.add(conversion);
    }

    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("Nenhuma conversão foi realizada até agora.");
        } else {
            System.out.println("Histórico de Conversões:");
            //for sem as chaves {}
            for (CurrencyConversion recordCurrency : history) System.out.println(recordCurrency.toString());
        }
    }
}

