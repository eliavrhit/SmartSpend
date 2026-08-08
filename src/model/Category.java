package model;

public class Category {
    private String name;
    private double monthlyLimit;

    public Category(String name, double monthlyLimit) {
        this.name = name;
        this.monthlyLimit = monthlyLimit;
    }

    public Category(String name) {
        this(name, 0.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    @Override
    public String toString() {
        return name + (monthlyLimit > 0 ? " (תקציב: ₪" + monthlyLimit + ")" : "");
    }
}