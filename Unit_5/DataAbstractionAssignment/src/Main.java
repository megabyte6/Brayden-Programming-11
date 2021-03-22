import java.util.Date;

public class Main {
    public static void main(String args[]) {
        // Test Customer.java
        Customer testCustomer = new Customer("Johnathen", 23432423, 550.25, 50.0);
        System.out.println("400.0 : " + testCustomer.deposit(400.0, new Date(), "Checking"));
        System.out.println("500.0 : " + testCustomer.deposit(500.0, new Date(), "Saving"));

        System.out.println("356.5 : " + testCustomer.withdraw(356.5, new Date(), "Checking"));
        System.out.println("553.0 : " + testCustomer.withdraw(553.0, new Date(), "Saving"));
        // Check if random account names work
        System.out.println("0.0 : " + testCustomer.withdraw(55.0, new Date(), "saving"));
        System.out.println("0.0 : " + testCustomer.withdraw(34.0, new Date(), "vhalojhf"));
        // Check if withdrawing past the overflow works
        System.out.println("0.0 : " + testCustomer.withdraw(1000.0, new Date(), "Checking"));
        System.out.println("0.0 : " + testCustomer.withdraw(500.0, new Date(), "Saving"));

        // Test Deposit.java
        Deposit checkingDeposit = new Deposit(30.0, new Date(), "Checking");
        Deposit savingDeposit = new Deposit(158.5, new Date(), "Saving");
        System.out.println("Deposit of: $30.0 Date: " + new Date() + " into account: Checking | "
                + checkingDeposit.toString());
        System.out.println("Deposit of: $158.5 Date: " + new Date() + " into account: Saving | "
                + savingDeposit.toString());
        
        // Test Withdraw.java
        Withdraw checkingWithdrawal = new Withdraw(30.0, new Date(), "Checking");
        Withdraw savingWithdrawal = new Withdraw(158.5, new Date(), "Saving");
        System.out.println("Withdrawal of: $30.0 Date: " + new Date() + " into account: Checking | "
                + checkingWithdrawal.toString());
        System.out.println("Withdrawal of: $158.5 Date: " + new Date() + " into account: Saving | "
                + savingWithdrawal.toString());
    }
}