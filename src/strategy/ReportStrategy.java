package strategy;

import model.Transaction;
import java.util.List;

public interface ReportStrategy {
    void generateReport(List<Transaction> transactions);
}