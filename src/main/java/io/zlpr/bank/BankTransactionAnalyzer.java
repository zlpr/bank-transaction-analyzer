package io.zlpr.bank;

import io.zlpr.bank.exporter.Exporter;
import io.zlpr.bank.parser.BankStatementParser;
import io.zlpr.bank.parser.CSVSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static String RESOURCES = "src/main/resources/";

    public  void analyze(String fileName, BankStatementParser parser, Exporter exporter) throws IOException, CSVSyntaxException {
        final Path path = Path.of(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = parser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        System.out.println(exporter.export(bankStatementProcessor.calculateTotalAmount()));
        System.out.println("The total in month for transactions is: " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));

    }




}
