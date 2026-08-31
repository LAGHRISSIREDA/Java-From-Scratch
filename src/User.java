import java.util.Locale;

public class User {

    private final String email;
    private final String password;

    public User(String email, String password){
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty !!");
        }

        String normalizeEmail = email.trim().toLowerCase(Locale.ROOT);

        if(!normalizeEmail.contains("@")){
            throw new IllegalArgumentException("Email format is Invalid !");
        }

        if(password == null || password.length()<5){
            throw new IllegalArgumentException("Password must contain at least 8 characters !!");
        }

        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean hasPassword(String entreredPassword){
        return this.password.equals(entreredPassword);
    }
    
}
