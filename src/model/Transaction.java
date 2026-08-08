package model;

import java.time.LocalDate;

public class Transaction {
    private String id;
    private double amount;
    private LocalDate date;
    private Category category;
    private String description;
    private TransactionType type;

    // בנאי המקבל 6 פרמטרים
    public Transaction(String id, double amount, LocalDate date, Category category, String description, TransactionType type) {
        if (amount <= 0) {
            throw new IllegalArgumentException("הסכום חייב להיות חיובי");
        }
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
        this.type = type;
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public Category getCategory() { return category; }
    public String getDescription() { return description; }
    public TransactionType getType() { return type; }

    @Override
    public String toString() {
        return String.format("[%s] %s | ₪%.2f | %s | %s (%s)",
                date, type == TransactionType.INCOME ? "+" : "-", amount, category.getName(), description, type);
    }
}