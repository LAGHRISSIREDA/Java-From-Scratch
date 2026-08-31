import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository{

    private final Map<String,User> usersByEmail = new HashMap<>();

    @Override
    public void save(User user){
        usersByEmail.put(user.getEmail(), user);
    }

    @Override
    public User findByEmail(String email){

        if(email == null){
            return null;
        }

        String normalizeEmail = email.trim().toLowerCase(Locale.ROOT);
        return usersByEmail.get(normalizeEmail);

    }

    @Override
    public boolean existsByEmail(String email){

        return findByEmail(email) != null;

    }

    @Override
    public List<User> findAll(){

        return new ArrayList<>(usersByEmail.values());

    }
    
}
