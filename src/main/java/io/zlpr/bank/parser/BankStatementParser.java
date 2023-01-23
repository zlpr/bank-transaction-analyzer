package io.zlpr.bank.parser;

import io.zlpr.bank.BankTransaction;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(final String line) throws CSVSyntaxException;
    List<BankTransaction> parseLinesFrom(List<String> lines) throws CSVSyntaxException;
}
