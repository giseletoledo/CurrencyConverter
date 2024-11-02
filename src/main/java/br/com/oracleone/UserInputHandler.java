package br.com.oracleone;

import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInputHandler {
    private final ConnectionApi connectionApi;
    private final Scanner scanner;
    private final ConversionHistory conversionHistory;
    private static final Logger logger = Logger.getLogger(UserInputHandler.class.getName());

    public UserInputHandler(ConnectionApi connectionApi) {
        this.connectionApi = connectionApi;
        this.scanner = new Scanner(System.in);
        this.conversionHistory = new ConversionHistory();

        // Configurações do logger
        logger.setLevel(Level.INFO); // Exibe mensagens de nível INFO e superior
    }

    public void handleUserInput() {
        int option;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Converter Moeda");
            System.out.println("2. Converter Valor Específico");
            System.out.println("3. Ver Histórico de Conversões");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (option) {
                case 1:
                    convertCurrency();
                    break;
                case 2:
                    convertSpecificValue();
                    break;
                case 3:
                    conversionHistory.displayHistory();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }


    private void convertCurrency() {
        Set<String> availableCurrencies = connectionApi.getAvailableCurrencies();

        // Exibe a lista de moedas disponíveis antes de solicitar a entrada do usuário
        System.out.println("Códigos de moeda disponíveis: " + availableCurrencies);

        System.out.print("Digite o código da moeda de origem: ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        while (true) {
            if (availableCurrencies.contains(fromCurrency)) {
                break; // A moeda é válida, sai do loop
            } else {
                System.out.println("Código de moeda inválido. Tente novamente.");
                System.out.print("Digite o código da moeda de origem: ");
                fromCurrency = scanner.nextLine().toUpperCase();
            }
        }

        System.out.print("Digite o código da moeda de destino: ");
        String toCurrency = scanner.nextLine().toUpperCase();
        while (true) {
            if (availableCurrencies.contains(toCurrency)) {
                break; // A moeda é válida, sai do loop
            } else {
                System.out.println("Código de moeda inválido. Tente novamente.");
                System.out.print("Digite o código da moeda de destino: ");
                toCurrency = scanner.nextLine().toUpperCase();
            }
        }

        Currency currency = connectionApi.convert(fromCurrency, toCurrency);

        // Mostra a conversão ao usuário
        System.out.println("Conversão realizada: " + currency);

        // Adiciona ao histórico
        conversionHistory.addConversion(currency);
    }

    private void convertSpecificValue() {
        Set<String> availableCurrencies = connectionApi.getAvailableCurrencies();

        // Exibe a lista de moedas disponíveis antes de solicitar a entrada do usuário
        System.out.println("Códigos de moeda disponíveis: " + availableCurrencies);

        System.out.print("Digite o código da moeda de origem: ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        while (!availableCurrencies.contains(fromCurrency)) {
            System.out.print("Código de moeda inválido. Tente novamente: ");
            fromCurrency = scanner.nextLine().toUpperCase();
        }

        System.out.print("Digite o código da moeda de destino: ");
        String toCurrency = scanner.nextLine().toUpperCase();
        while (!availableCurrencies.contains(toCurrency)) {
            System.out.print("Código de moeda inválido. Tente novamente: ");
            toCurrency = scanner.nextLine().toUpperCase();
        }

        System.out.print("Digite o valor a ser convertido: ");
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.print("O valor deve ser maior que zero. Tente novamente: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número válido: ");
            }
        }

        // Obter a taxa de conversão da API
        Currency currency = connectionApi.convert(fromCurrency, toCurrency);
        double convertedValue = amount * currency.conversion_rate();

        // Mostra o resultado da conversão ao usuário
        System.out.printf("Conversão de %.2f %s para %s: %.2f%n", amount, fromCurrency, toCurrency, convertedValue);

        // Adiciona ao histórico usando o objeto Currency
        conversionHistory.addConversion(currency);
    }
}
