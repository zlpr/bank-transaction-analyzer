package io.zlpr.bank;

import io.zlpr.bank.parser.BankStatementCSVParser;
import io.zlpr.bank.parser.BankStatementParser;
import io.zlpr.bank.parser.CSVSyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {

    private static BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine(){
        final String line = "30-01-2017,-50,Tesco";
        BankTransaction result = null;
        try {
            result = statementParser.parseFrom(line);
        } catch (CSVSyntaxException e) {
            fail ();
        }
        final BankTransaction expected
                = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        assertEquals(expected.date(), result.date());
        assertEquals(expected.amount(), result.amount(), tolerance);
        assertEquals(expected.description(), result.description());
    }


}