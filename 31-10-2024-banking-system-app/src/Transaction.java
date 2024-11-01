import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    public enum TransactionType{DEPOSIT, WITHDRAWAL, TRANSFER}

    private String transactionId;
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private Account account;
    private Account targetAccount;

    public Transaction(TransactionType type, double amount, Account account)
    {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.account = account;
    }

    public Transaction(TransactionType type, double amount, Account account, Account targetAccount)
    {
        this(type,amount,account);
        this.targetAccount = targetAccount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public String getTransactionDetails()
    {
        String details = "Transaction ID: " + transactionId + "\nType: " + type + "\nAmount: $" + amount + "\nDate: " + date + "\nAccount: " + account.getAccountNumber();
        if(type == TransactionType.TRANSFER && targetAccount != null)
        {
            details += "\nTarget Account: " + targetAccount.getAccountNumber();
        }
        return details;
    }
}
