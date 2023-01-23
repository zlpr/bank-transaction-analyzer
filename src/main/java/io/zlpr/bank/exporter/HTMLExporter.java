package io.zlpr.bank.exporter;

import io.zlpr.bank.SummaryStatistics;
import io.zlpr.bank.exporter.Exporter;

public class HTMLExporter implements Exporter {
    @Override
    public String export(SummaryStatistics summarystatistics) {

        return
                """
                        <!doctype html>
                        <html lang='en'>
                        <head><title>Bank Transaction Report</title></head>
                        <body>
                        <ul>
                        <li><strong>The sum is</strong>: %.2f</li>
                        <li><strong>The average is</strong>: %.2f</li>
                        <li><strong>The max is</strong>: %.2f</li>
                        <li><strong>The min is</strong>: %.2f</li>
                        </ul>
                        </body>
                        </html>""".formatted(summarystatistics.sum(),
                        summarystatistics.average(),
                        summarystatistics.max(),
                        summarystatistics.min());
    }
}
