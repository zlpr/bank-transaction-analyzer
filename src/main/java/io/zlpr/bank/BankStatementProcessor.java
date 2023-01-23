package io.zlpr.bank;

import java.time.Month;
import java.util.List;
import java.util.function.Predicate;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public SummaryStatistics summarizeTransactions(Predicate<BankTransaction> filter) {
        var summary = bankTransactions.stream()
                .filter(filter)
                .mapToDouble(BankTransaction::amount)
                .summaryStatistics();
        return new SummaryStatistics(summary.getSum(), summary.getMax(),
                summary.getMin(), summary.getAverage());
    }

    public List<BankTransaction> findTransactions(Predicate<BankTransaction> filter) {
        return bankTransactions.stream()
                .filter(filter)
                .toList();
    }

    public SummaryStatistics calculateTotalAmount() {
        return summarizeTransactions(t -> true);
    }

    public SummaryStatistics calculateTotalForCategory(String category) {
        return summarizeTransactions(transaction ->
                transaction.description().equals(category));
    }

    public SummaryStatistics calculateTotalInMonth(Month month) {
        return summarizeTransactions(transaction ->
                transaction.date().getMonth() == month);
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(int amount) {
        return findTransactions(transaction -> transaction.amount() <= amount);
    }
}

