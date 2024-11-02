package br.com.oracleone;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Set;

public class ConnectionApi {
    private final String apiKey;
    private static final String ENDPOINT_BASE = "https://v6.exchangerate-api.com/v6/";

    public ConnectionApi() {
        this.apiKey = loadApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ApiException("A chave da API não está definida no arquivo de propriedades.");
        }
    }

    private String loadApiKey() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new ApiException("Desculpe, não foi possível encontrar o arquivo de propriedades.");
            }
            properties.load(input);
            return properties.getProperty("API_KEY");
        } catch (IOException e) {
            throw new ApiException("Erro ao carregar o arquivo de propriedades: " + e.getMessage(), e);
        }
    }

    // Converter moedas
    public Currency convert(String fromCurrency, String toCurrency) {
        String url = ENDPOINT_BASE + apiKey + "/pair/" + fromCurrency + "/" + toCurrency;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                Gson gson = new Gson();
                Currency currency = gson.fromJson(response.body(), Currency.class);

                // Aqui você pode verificar o resultado da resposta
                if (currency.result() == null || currency.conversion_rate() == null) {
                    throw new ApiException("Erro ao obter a taxa de câmbio: resposta da API inválida.");
                }
                return currency;
            } catch (IOException e) {
                throw new ApiException("Erro ao acessar a API: " + e.getMessage(), e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura o status de interrupção
                throw new ApiException("A chamada da API foi interrompida.", e);
            }
        }
    }

    //Buscar moedas disponíveis
    public Set<String> getAvailableCurrencies() {
        String url = ENDPOINT_BASE + apiKey + "/latest/USD";
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
                JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

                return conversionRates.keySet();
            } catch (IOException e) {
                throw new ApiException("Erro ao acessar a API: " + e.getMessage(), e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura o status de interrupção
                throw new ApiException("A chamada da API foi interrompida.", e);
            }
        }
    }
}
