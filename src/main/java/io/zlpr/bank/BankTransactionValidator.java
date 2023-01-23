package io.zlpr.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BankTransactionValidator {
    private static final DateTimeFormatter DATE_PATTERN =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String date;
    private final String amount;
    private final String description;

    public BankTransactionValidator(String date, String amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Notification validate() {
        final Notification notification = new Notification();

        if (this.description.length() > 100) {
            notification.addError("The description is too long");
        }

        try {
            LocalDate parsedDate = LocalDate.parse(this.date, DATE_PATTERN);
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in the future");
            }
        } catch (DateTimeParseException e) {
            notification.addError("Invalid format for date");
        }

        try {
            Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            notification.addError("Invalid format for amount");
        }


        return notification;
    }
}
