import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        boolean running = true;
        UserRepository userRepository = new InMemoryUserRepository();
        AuthService authService = new AuthService(userRepository);

        System.out.println("Welcome to Auth : ");

        while (running) {

            printMenu();
            System.out.println("Choose an option : ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> register(sc,authService);
                case "2" -> login(sc,authService);
                case "3" -> listUsers(authService);
                case "4" -> {
                            running = false;
                            System.out.println("Application Stopped");
                            }
                default -> System.out.println("Invalid Option !!");
            }

            
        }

        sc.close();
    }



    public static void printMenu(){
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Lister");
        System.out.println("4. Exit");
    }

    public static void register(Scanner sc,AuthService authService){
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.nextLine();
        System.out.println("Registration received for "+email);
        try {
            boolean registred = authService.register(email, password);
            if(registred){
                System.out.println("Resgitration successful.");
            }else{
                System.out.println(
                    "Registration Failed: "+
                    "email is already registred."
                );
            }
        } catch (IllegalArgumentException e) {
            System.out.println(
                "Registration Failed: "+
                e.getMessage()
            );
        }
    }

    public static void login(Scanner sc ,AuthService authService){
        
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.nextLine();
        System.out.println("Login received for "+email);

        User user = authService.login(email, password);
        if(user != null){
            System.out.println(
                "Login successful! Welcome "+
                user.getEmail()
            );
        }else{
            System.out.println(
                "Invalid email or password !!!!!!"
            );
        }
    }

    public static void listUsers(AuthService authService){

        List<User> users = authService.findAllUsers();

        if(users.isEmpty()){
            System.out.println("No users are Registred !!");
            return;
        }

        System.out.println("Registred users : "+users.size());
        
        for (User user : users) {
            System.out.println("Email : "+user.getEmail());
            
        }
        
    }





    
}