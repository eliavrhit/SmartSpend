package ui;

import model.Category;
import model.Transaction;
import model.TransactionType;
import service.BudgetManager;
import strategy.CategorySummaryStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final BudgetManager manager = new BudgetManager();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("בחר אפשרות: ");

            switch (choice) {
                case 1 -> addTransactionUI();
                case 2 -> showAllTransactions();
                case 3 -> manager.executeReport(new CategorySummaryStrategy());
                case 4 -> {
                    System.out.println("להתראות!");
                    running = false;
                }
                default -> System.out.println("בחירה לא תקינה, נסה שוב.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== SmartSpend - ניהול תקציב אישי ===");
        System.out.println("1. הוספת תנועה (הכנסה/הוצאה)");
        System.out.println("2. הצגת כל התנועות");
        System.out.println("3. הפקת דוח הוצאות לפי קטגוריות");
        System.out.println("4. יציאה");
    }

    private void addTransactionUI() {
        System.out.println("\n--- הוספת תנועה חדשה ---");
        System.out.println("1. הכנסה (+)");
        System.out.println("2. הוצאה (-)");
        int typeChoice = getIntInput("סוג התנועה: ");
        TransactionType type = (typeChoice == 1) ? TransactionType.INCOME : TransactionType.EXPENSE;

        double amount = getDoubleInput("הכנס סכום: ");

        System.out.println("בחר קטגוריה:");
        List<Category> categories = manager.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }
        int catChoice = getIntInput("מספר קטגוריה: ") - 1;
        Category category = (catChoice >= 0 && catChoice < categories.size())
                ? categories.get(catChoice)
                : categories.get(0);

        System.out.print("תיאור קצר: ");
        String description = scanner.nextLine();

        manager.addTransaction(amount, LocalDate.now(), category, description, type);
        System.out.println("✅ התנועה נוספה בהצלחה!");
    }

    private void showAllTransactions() {
        System.out.println("\n--- היסטוריית תנועות ---");
        if (manager.getTransactions().isEmpty()) {
            System.out.println("אין תנועות רשומות במערכת.");
            return;
        }
        manager.getTransactions().forEach(System.out::println);
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("קלט לא תקין. הכנס מספר: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // ניקוי ה-Buffer
        return val;
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("קלט לא תקין. הכנס סכום מספרי: ");
            scanner.next();
        }
        double val = scanner.nextDouble();
        scanner.nextLine(); // ניקוי ה-Buffer
        return val;
    }
}