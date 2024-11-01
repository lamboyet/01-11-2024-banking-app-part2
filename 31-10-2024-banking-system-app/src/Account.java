import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private String accountType;
    private User user;
    private List<Transaction> transactions;

    public Account(String accountNumber, double balance, String accountType, User user) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void depositAmount(double amount)
    {
        if(amount > 0)
        {balance += amount;
            System.out.println("deposit succesful. new balance: " + balance);
        }else{
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount)
    {
        if(amount > 0 && amount <= balance)
        {
            balance -=amount;
            System.out.println("withdrawal succesful. new balance: " + balance);
        }
        else {
            System.out.println("invalid withdrawal amount or insufficient funds");
        }
    }

    public void transferFunds(Account targetAccount, double amount)
    {
        if(amount > 0 && amount <= balance)
        {
            this.withdraw(amount);
            targetAccount.depositAmount(amount);
            System.out.println("Transfer succesful. New Balance: " + balance);
        }else{
            System.out.println("Invalid transfer amount or insufficient funds");
        }
    }


    public List<Transaction> getTransaction() {
        return transactions;
    }
}
