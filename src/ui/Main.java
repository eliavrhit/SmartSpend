package ui;

import model.Category;
import model.TransactionType;
import service.BudgetManager;
import strategy.CategorySummaryStrategy;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BudgetManager manager = new BudgetManager();

        // שליפת קטגוריה קיימת מהרשימה
        Category foodCategory = manager.getCategories().get(0); // מזון

        // הוספת תנועות לדוגמה
        manager.addTransaction(150.0, LocalDate.now(), foodCategory, "קניות בסופר", TransactionType.EXPENSE);
        manager.addTransaction(2200.0, LocalDate.now(), foodCategory, "קניות לבית", TransactionType.EXPENSE);

        // הרצת דוח לפי אסטרטגיה
        manager.executeReport(new CategorySummaryStrategy());
    }
}