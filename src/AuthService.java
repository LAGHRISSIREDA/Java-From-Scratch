import java.util.List;
import java.util.Objects;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = Objects.requireNonNull(
            userRepository,
            "UserRepository cannot be null!!"
        );

    }

    public boolean register(String email, String password){

        User user = new User(email, password);

        if(userRepository.existsByEmail(user.getEmail())){
            return false;
        }

        userRepository.save(user);
        return true;

    }

    public User login(String email, String password){

        User user = userRepository.findByEmail(email);

        if(user != null && user.hasPassword(password)){
            return user;
        }

        return null;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
    
}
