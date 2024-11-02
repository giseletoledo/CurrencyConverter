# <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-plain.svg"  width="6%" />  Currency Conversion App 
          

## Descrição

O Currency Conversion App é uma aplicação Java que permite aos usuários converter valores entre diferentes moedas. 
O aplicativo utiliza a [ExchangeRate API](https://www.exchangerate-api.com/docs/overview) para obter as taxas de câmbio mais recentes e oferece um histórico de conversões realizadas.

## Funcionalidades
Conversão de valores entre diferentes moedas.
Exibição de moedas disponíveis.
Histórico das últimas conversões realizadas.
Registros de logs para rastrear as conversões.

## Pré-requisitos
Java 21 - Java Development Kit (JDK): Certifique-se de ter o JDK instalado em seu sistema. Você pode baixar a versão mais recente do site oficial do Java.

Maven (opcional, se você não tiver o Maven instalado, siga as instruções abaixo para compilar e executar o projeto manualmente).
Clone o repositório.

## Dependências do Projeto

O projeto utiliza as seguintes dependências:

xml
```
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.source.encoding>
</properties>

<dependencies>
    <!-- Biblioteca Gson para manipulação de JSON -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
</dependencies>
```

## Configuração da API Key
Para utilizar a API de conversão de moedas, você precisará de uma chave de API da ExchangeRate-API. Siga os passos abaixo para configurar sua chave de API:

1. Crie uma conta: Acesse ExchangeRate-API e crie uma conta gratuita.
2. Obtenha sua API Key: Após a criação da conta, você receberá uma chave de API que será utilizada para autenticação em suas requisições.
3. Configurar o arquivo de propriedades:
4. Navegue até a pasta src/main/resources no seu projeto.
5. Crie um arquivo chamado config.properties (se ele não existir).
6. Adicione a seguinte linha ao arquivo, substituindo YOUR_API_KEY pela sua chave de API obtida:

```
API_KEY=YOUR_API_KEY
```

## Como Usar

1. Ao iniciar o aplicativo, você verá um menu com opções.
2. Escolha uma opção:
3. Converter Moeda: Digite os códigos das moedas de origem e destino, bem como o valor a ser convertido.
4. Ver Histórico de Conversões: Exibe o histórico das conversões realizadas.
5. Sair: Encerra o aplicativo.

Siga as instruções fornecidas na tela para realizar as conversões.

### Exemplo de Uso

```
Escolha uma opção:
1. Converter Moeda
2. Converter Valor Específico
3. Ver Histórico de Conversões
0. Sair
Códigos de moeda disponíveis: [USD, EUR, BRL, ...]
Digite o código da moeda de origem: USD
Digite o código da moeda de destino: EUR
Digite o valor a ser convertido: 100
Conversão realizada: O valor de 100 USD em EUR é 85.50
```
