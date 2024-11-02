package br.com.oracleone;

public class Main {
    public static void main(String[] args) {
        ConnectionApi connectionApi = new ConnectionApi();
        UserInputHandler userInputHandler = new UserInputHandler(connectionApi);
        userInputHandler.handleUserInput();
    }
}
