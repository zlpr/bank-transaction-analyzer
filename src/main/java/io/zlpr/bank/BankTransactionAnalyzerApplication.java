package io.zlpr.bank;

import io.zlpr.bank.exporter.Exporter;
import io.zlpr.bank.exporter.HTMLExporter;
import io.zlpr.bank.exporter.JSONExporter;
import io.zlpr.bank.parser.BankStatementCSVParser;
import io.zlpr.bank.parser.BankStatementParser;
import io.zlpr.bank.parser.CSVSyntaxException;

import java.io.IOException;

public class BankTransactionAnalyzerApplication {

    public static void main(String[] args) throws IOException, CSVSyntaxException {
        final BankStatementParser parser = new BankStatementCSVParser();
        final BankTransactionAnalyzer analyzer = new BankTransactionAnalyzer();
        final Exporter exporter = new JSONExporter();

        analyzer.analyze("data.csv",parser,exporter);


    }
}
