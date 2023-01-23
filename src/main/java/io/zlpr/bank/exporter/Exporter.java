package io.zlpr.bank.exporter;

import io.zlpr.bank.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summarystatistics);
}
