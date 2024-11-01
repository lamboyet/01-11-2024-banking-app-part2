import java.util.Scanner;

public class Main {

        private static Bank bank = new Bank();
        private static Authentication auth = new Authentication(bank.getUserDatabase());
        private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true)
        {
            System.out.println("----Banking system ----- ");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");
            System.out.print("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("exiting application");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }


    }
    private static void registerUser(){
        System.out.println("Enter user id: ");
        String userId = scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(userId, name, password);
        bank.registerUser(newUser);
        System.out.println("User registered succesfully");
    }

    private static void loginUser()
    {
        System.out.println("Enter user id: ");
        String userId = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if(auth.authenticate(userId, password))
        {
            System.out.println("Login succesful");
            userMenu(userId);

        }else{
            System.out.println("Login failed. Please try again");
        }
    }

    private static void userMenu(String userId)
    {
        while(true)
        {
            System.out.println("----user menu-----");
            System.out.println("1.create account");
            System.out.println("2.deposit");
            System.out.println("3.withdraw");
            System.out.println("4.transfer");
            System.out.println("5.View transaction history");
            System.out.println("6.login");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    createAccount(userId);
                    break;
                case 2:
                    processDeposit(userId);
                    break;
                case 3:
                    processWithdrawal(userId);
                    break;
                case 4:
                    processTransfer(userId);
                    break;
                case 5:
                    viewTransactionHistory(userId);
                    break;
                case 6:
                    System.out.println("Logging out... ");
                    return;
                default:
                    System.out.println("invalid choice, please try again");
            }
        }
    }

    private static void createAccount(String userId)
    {
        User user = bank.getUserDatabase().get(userId);
        if(user == null)
        {
            System.out.println("user not found");
            return;
        }

        System.out.println("Enter Account Type (SAVINGS, CHECKING): ");
        String accountType = scanner.nextLine();
        System.out.println("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();
        bank.createAccount(user, accountType, initialBalance);
        System.out.println("Account created succesfully. ");
    }

    private static void processDeposit(String userId)
    {
        System.out.println("Enter Account number: ");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        bank.processDeposit(accountNumber,amount);
        System.out.println("deposit succesful");
    }

    private static void processWithdrawal(String userId)
    {
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter Withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        bank.processWithdrawal(accountNumber,amount);
        System.out.println("Withdrawal succesful");
    }

    private static void processTransfer(String userId){
        System.out.println("Enter Source Account Number: ");
        String fromAccountNumber = scanner.nextLine();
        System.out.println("Enter Target Account Number: ");
        String toAccountNumber = scanner.nextLine();
        System.out.println("enter transfer amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        bank.processTransfer(fromAccountNumber,toAccountNumber,amount);
        System.out.println("Transfer succesful.");
    }

    private static void viewTransactionHistory(String userId)
    {
        System.out.println("enter Account number: ");
        String accountNumber = scanner.nextLine();
        bank.viewTransactionHistory(accountNumber);
    }

}
