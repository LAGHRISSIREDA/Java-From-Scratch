import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = Objects.requireNonNull(
            userRepository,
            "UserRepository cannot be null!!"
        );

    }

    public UserResponse register(RegisterRequest request){
        Objects.requireNonNull(
            request,
            "ResgitrationRequest Cannot be Null!!"
        );

        User user = new User(request.email(), 
                            request.password(),
                            Role.USER);

        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateEmailException();
        }

        userRepository.save(user);

        //we need to transter user to userReponse object Here
        //but first i need to create a mfunction that transforms the user into userResponse toResponse
        return toReponse(user);

    }

    //transform user to userReponse
    private UserResponse toReponse(User user){
        return new UserResponse(user.getEmail(),
                                user.getRole());
    }

    public UserResponse login(LoginRequest request){
        Objects.requireNonNull(
            request,
            "LoginRequest Cannot be Null!!"
        );

        User user = userRepository
                    .findByEmail(request.email())
                    .filter(foundUser -> 
                        foundUser.hasPassword(request.password())
                    )
                    .orElseThrow(
                        InvalidCredentialsException::new
                    );

        return toReponse(user);
    }

    public List<UserResponse> findAllUsers(){
        return userRepository
                .findAll()
                .stream()
                .map(this::toReponse)
                .toList();
        
    }
    
}
