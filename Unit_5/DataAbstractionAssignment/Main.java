import java.util.Date;

public class Main {
    public static void main(final String[] args) {
        final Customer test = new Customer("Johnn", 23432423, 500.25, 50.0);
        test.deposit(400.0, new Date(118, 10, 4), "Checking");
        test.deposit(500.0, new Date(), "Saving");
        test.displayDeposits();
        System.out.println(test.deposit(500.0, new Date(), "Saving"));
    }
}