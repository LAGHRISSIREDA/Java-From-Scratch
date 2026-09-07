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

        User user = new User(request.email(), request.password());

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

        User user = userRepository.findByEmail(request.email());

        if(user == null && !user.hasPassword(request.password())){
            throw new InvalidCredentialsException();
        }

        return toReponse(user);
    }

    public List<UserResponse> findAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();

        // for(User user:users){
        //     responses.add(toReponse(user));
        // }

        // return responses;
        return users.stream().map(this::toReponse).toList();
    }
    
}
