package lotto.utils;

public class Log {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    public Log() {
    }

    public void error(String message) {
        System.out.println(ERROR_MESSAGE + message);
    }

    public void info(String input) {
        System.out.println(input);
    }
}
