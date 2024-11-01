import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private String userId;
    private String name;
    private String contactInfo;
    private String password;
    private boolean isLocked;

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String inputPassword)
    {
        if(this.password ==null){
            System.out.println("Error: user password is not initialized");
            return false;
        }
        String hashedInput = hashPassword(inputPassword);
        System.out.println("Stored hash: " + this.password);
        System.out.println("Input hash: " + hashedInput);
        return this.password.equals(hashedInput);
    }

    public void updateContactInfo(String newContactInfo)
    {
        this.contactInfo = newContactInfo;
    }

    private String hashPassword(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hash = new StringBuilder();
            for(byte b : hashedBytes)
            {
                hash.append(String.format("%02x",b));
            }
            return hash.toString();
        }catch (NoSuchAlgorithmException e){throw new RuntimeException("Error: password hashing alghorithm not found");}
    }

    public boolean isLocked()
    {
        return isLocked;
    }

    public void setLocked(boolean isLocked)
    {
        this.isLocked = isLocked;
    }
}
