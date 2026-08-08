package service;

import model.Category;
import model.Transaction;
import model.TransactionType;
import strategy.ReportStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BudgetManager {
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();

    public BudgetManager() {
        categories.add(new Category("מזון", 2000));
        categories.add(new Category("מגורים"));
        categories.add(new Category("בילויים", 800));
        categories.add(new Category("משכורת"));
    }

    public void addTransaction(double amount, LocalDate date, Category category, String description, TransactionType type) {
        Transaction t = new Transaction(UUID.randomUUID().toString(), amount, date, category, description, type);
        transactions.add(t);
        checkBudgetAlert(category);
    }

    private void checkBudgetAlert(Category category) {
        if (category.getMonthlyLimit() <= 0) return;

        double totalSpent = transactions.stream()
                .filter(t -> t.getCategory().getName().equalsIgnoreCase(category.getName()))
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();

        if (totalSpent > category.getMonthlyLimit()) {
            System.out.printf("⚠️ התראה: חרגת מהתקציב בקטגוריה '%s'! (הוצאה: ₪%.2f / יעד: ₪%.2f)%n",
                    category.getName(), totalSpent, category.getMonthlyLimit());
        }
    }

    public void executeReport(ReportStrategy strategy) {
        strategy.generateReport(transactions);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}