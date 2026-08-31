import java.util.List;

public interface UserRepository {

    void save(User user);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAll();

}