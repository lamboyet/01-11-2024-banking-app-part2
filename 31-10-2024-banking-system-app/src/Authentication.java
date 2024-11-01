import java.util.HashMap;
import java.util.Map;

public class Authentication {

    private Map<String, User> userDatabase;
    private Map<String, Integer> loginAttempts;
    private static final int MAX_ATTEMPTS = 3;

    public Authentication(Map<String, User> userDatabase)
    {
        this.userDatabase = userDatabase;
        this.loginAttempts = new HashMap<>();
    }

    public boolean authenticate(String userId, String password)
    {
        User user = userDatabase.get(userId);
        if(user == null)
        {
            System.out.println("user not fount");
            return false;
        }
        if(isAccountLocked(userId))
        {
            System.out.println("Account is locked due to multiple failed login attempts");
            return false;
        }

        if(user.verifyPassword(password))
        {
            System.out.println("Login successful");
            resetLoginAttempts(userId);
            return true;
        } else {
            incrementLoginAttempts(userId);
            System.out.println("Invalid password");
            if(loginAttempts.get(userId)>=MAX_ATTEMPTS)
            {
                lockAccount(userId);
            }
            return false;
        }
    }

    private void incrementLoginAttempts(String userId)
    {
        int attempts = loginAttempts.getOrDefault(userId,0) + 1;
        loginAttempts.put(userId,attempts);
    }

    private void resetLoginAttempts(String userId)
    {
        loginAttempts.put(userId,0);
    }

    private void lockAccount(String userId)
    {
        User user = userDatabase.get(userId);
        if(user != null)
        {
            user.setLocked(true);
            System.out.println("Account locked due to too many failed login attempts");
        }
    }

    public boolean isAccountLocked(String userId)
    {
        User user = userDatabase.get(userId);
        return user != null && user.isLocked();

    }


}
