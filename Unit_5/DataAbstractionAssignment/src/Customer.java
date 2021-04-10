import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits = new ArrayList<Deposit>();
    private ArrayList<Withdraw> withdraws = new ArrayList<Withdraw>();
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer() {
        this.name = "Unknown";
        this.accountNumber = 0;
        this.checkBalance = 0.0;
        this.savingBalance = 0.0;
        this.savingRate = 0.001;
    }

    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = checkDeposit;
        this.savingBalance = savingDeposit;
        this.savingRate = 0.001;
    }

    // Requires: double, Date, String
    // Modifies: this
    // Effects: Adds a diposit to the list of deposits and adds more money to the account specified
    public double deposit(double amt, Date date, String account) {
        double addedToAccount = 0.0;
        if (account.equals(CHECKING)) {
            deposits.add(new Deposit(amt, date, CHECKING));
            checkBalance += amt;
            addedToAccount = amt;
        } else if (account.equals(SAVING)) {
            deposits.add(new Deposit(amt, date, SAVING));
            savingBalance += amt;
            addedToAccount = amt;
        }
        return addedToAccount;
    }

    // Requires: double, Date, String
    // Modifies: this
    // Effects: Adds a withdraw to the list of withdraws and removes money from the account
    // specified
    public double withdraw(double amt, Date date, String account) {
        double removedFromAccount = 0.0;
        if (account.equals(CHECKING)) {
            if (checkOverdraft(amt, CHECKING)) {
                return 0.0;
            }
            withdraws.add(new Withdraw(amt, date, CHECKING));
            checkBalance -= amt;
            removedFromAccount = amt;
        } else if (account.equals(SAVING)) {
            if (checkOverdraft(amt, SAVING)) {
                return 0.0;
            }
            withdraws.add(new Withdraw(amt, date, SAVING));
            savingBalance -= amt;
            removedFromAccount = amt;
        }
        return removedFromAccount;
    }

    // Requires: double, String
    // Effects: Checks if the withdrawal will cause the account number to drop below the overdraft
    // protection
    private boolean checkOverdraft(double amt, String account){
        boolean overdraft = false;
        if (account.equals(CHECKING)) {
            if ((checkBalance - amt) < OVERDRAFT) {
                overdraft = true;
            }
        } else if (account.equals(SAVING)) {
            if ((savingBalance - amt) < OVERDRAFT) {
                overdraft = true;
            }
        }
        return overdraft;
    }

    // Effects: Prints all of the deposits to the console
    public void displayDeposits() {
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }

    // Effects: Prints all of the withdraws to the console
    public void displayWithdraws() {
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

    // Getters and setters for most variables
    public int getAccountNumber() {
        return accountNumber;
    }

    public double getCheckBalance() {
        return checkBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double getSavingRate() {
        return savingRate;
    }

    public void setSavingRate(double savingRate) {
        this.savingRate = savingRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
