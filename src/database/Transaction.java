package database;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record Transaction(Customer sender, Customer recipient, double amount, LocalDateTime dateTime) {
    public Transaction(Customer sender, Customer recipient, double amount, LocalDateTime dateTime) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.dateTime = dateTime.truncatedTo(ChronoUnit.SECONDS);
    }

    public String toString() {
        return sender.toString() + " to " + recipient.toString() + ": " + amount + ", at " + dateTime.toString();
    }
}
