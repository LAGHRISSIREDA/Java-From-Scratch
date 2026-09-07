import java.util.Locale;
import  java.util.Objects;

public class User {

    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role){
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

        this.email = normalizeEmail;
        this.password = password;
        this.role = Objects.requireNonNull(
            role,
            "Role cannot be null !!"
        );
    }

    public String getEmail(){
        return this.email;
    }

    public Role getRole(){
        return this.role;
    }

    public boolean hasPassword(String entreredPassword){
        return this.password.equals(entreredPassword);
    }
    
}
