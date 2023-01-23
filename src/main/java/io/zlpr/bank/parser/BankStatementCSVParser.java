package io.zlpr.bank.parser;

import io.zlpr.bank.BankTransaction;
import io.zlpr.bank.BankTransactionValidator;
import io.zlpr.bank.Notification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser  implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String COLUMN_SEPARATOR = ",";
    private static final int EXPECTED_ATTRIBUTES_LENGTH = 3;

    public BankTransaction parseFrom(final String line) throws CSVSyntaxException {
        final String[] columns = line.split(COLUMN_SEPARATOR);

        if (columns.length != EXPECTED_ATTRIBUTES_LENGTH){
            throw new CSVSyntaxException(line);
        }
        final Notification notification =
                new BankTransactionValidator(columns[0],columns[1],columns[2]).validate();
        if (notification.hasErrors()){
            throw new IllegalArgumentException(notification.errorMessage());
        }

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFrom(List<String> lines) throws CSVSyntaxException {
        final List<BankTransaction> transactions = new ArrayList<>();

        for (String line : lines) {
            transactions.add(parseFrom(line));
        }

        return transactions;
    }
}
