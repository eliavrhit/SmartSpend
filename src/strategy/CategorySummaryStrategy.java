package strategy;

import model.Transaction;
import model.TransactionType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategorySummaryStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Transaction> transactions) {
        System.out.println("\n--- דוח התפלגות הוצאות לפי קטגוריה ---");

        Map<String, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        if (expensesByCategory.isEmpty()) {
            System.out.println("אין הוצאות רשומות במערכת.");
            return;
        }

        expensesByCategory.forEach((category, sum) ->
                System.out.printf("קטגוריה: %-15s | סה\"כ הוצאה: ₪%.2f%n", category, sum)
        );
    }
}