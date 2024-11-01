import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, Account> listOfAccounts;
    private Map<String, User> listOfUsers;
    private List<Transaction> transactionHistory;

    public Bank() {
        listOfAccounts = new HashMap<>();
        listOfUsers = new HashMap<>();
        transactionHistory = new ArrayList<>();

    }

    public void registerUser(User user) {
        if (!listOfUsers.containsKey(user.getUserId())) {
            listOfUsers.put(user.getUserId(), user);
            System.out.println("User registered succesfully");
        } else {
            System.out.println("user Id already exists");
        }
    }

    public void createAccount(User user, String accountType, double initialBalance) {
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(accountNumber, initialBalance, accountType, user);
        listOfAccounts.put(accountNumber, newAccount);
        System.out.println("Account created succesfully for user " + user.getName());
    }

    public Account findAccountByNumber(String accountNumber) {
        return listOfAccounts.get(accountNumber);
    }

    public void processDeposit(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            account.depositAmount(amount);
            Transaction transaction = new Transaction(Transaction.TransactionType.DEPOSIT, amount, account);
            transactionHistory.add(transaction);
        } else {
            System.out.println("Account not found");
        }
    }

    public void processWithdrawal(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            Transaction transaction = new Transaction(Transaction.TransactionType.WITHDRAWAL, amount, account);
            transactionHistory.add(transaction);
        } else {
            System.out.println("account not found");
        }
    }

    public void processTransfer(String fromAccountNumber, String toAccountNumber, double amount)
    {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);
        if(fromAccount != null && toAccount !=null) {
        fromAccount.transferFunds(toAccount,amount);
        Transaction transaction = new Transaction(Transaction.TransactionType.TRANSFER,amount,fromAccount, toAccount);
        transactionHistory.add(transaction);

        }else {
            System.out.println("One or both accounts not found");
        }
    }

    private String generateAccountNumber(){return "ACCT-" + (listOfAccounts.size() + 1);}

    public Map<String, User> getUserDatabase(){
        return listOfUsers;
    }

    public void viewTransactionHistory(String accountNumber)
    {
        Account account = listOfAccounts.get(accountNumber);
        if(account == null)
        {
            System.out.println("Account not found");
            return;
        }

        List<Transaction> transactions = account.getTransaction();
        if(transactions.isEmpty())
        {
            System.out.println("No transactions found for this account");
            return;
        }

        System.out.println("-----transactions history for account ------ " + accountNumber + "-----");
        for(Transaction transaction : transactions)
            System.out.println(transaction);
    }
}
