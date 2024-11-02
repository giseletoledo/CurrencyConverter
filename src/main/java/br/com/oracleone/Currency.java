package br.com.oracleone;

public record Currency(String result, String base_code, String target_code, Double conversion_rate) {
    @Override
    public String toString() {
        if ("success".equals(result)) {
            return "O valor de 1 " + base_code + " em " + target_code + " é " + conversion_rate;
        } else {
            return "Erro ao obter a taxa de câmbio.";
        }
    }
}


