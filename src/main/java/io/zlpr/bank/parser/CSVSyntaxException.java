package io.zlpr.bank.parser;

public class CSVSyntaxException extends IllegalAccessException{

    public CSVSyntaxException(String line) {
        super(line);
    }
}
