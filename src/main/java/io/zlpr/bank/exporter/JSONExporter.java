package io.zlpr.bank.exporter;

import io.zlpr.bank.SummaryStatistics;

public class JSONExporter implements Exporter {

    @Override
    public String export(SummaryStatistics summarystatistics) {
        return """
                {
                "sum": %.2f,
                "average": %.2f,
                "max": %.2f,
                "min": %.2f
                }
                """.formatted(summarystatistics.sum(),
                summarystatistics.average(),
                summarystatistics.max(),
                summarystatistics.min());
    }
}
