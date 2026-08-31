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

        System.out.println("Welcome to Auth : ");

        while (running) {

            printMenu();
            System.out.println("Choose an option : ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> register(sc,userRepository);
                case "2" -> login(sc,userRepository);
                case "3" -> listUsers(userRepository);
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

    public static void register(Scanner sc,UserRepository userRepository){
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.nextLine();
        System.out.println("Registration received for "+email);
        try {

            User user = new User(email, password);

            if(userRepository.existsByEmail(user.getEmail())){
                System.out.println("Resgitration Failed : Email Already Exists !!");
                return;
            }
            userRepository.save(user);
            System.out.println("Registration Successful : "+user.getEmail());
        } catch (IllegalArgumentException e) {
            System.out.println("Registration Failed : "+e.getMessage());
        }
    }

    public static void login(Scanner sc ,UserRepository userRepository){
        
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        System.out.println("Enter your password : ");
        String password = sc.nextLine();
        System.out.println("Login received for "+email);

        User user = userRepository.findByEmail(email);
        if(user != null && user.hasPassword(password)){
            System.out.println("Login successful for : "+user.getEmail());
        }else{
            System.out.println("Invalid Email or Password");
        }
    }

    public static void listUsers(UserRepository userRepository){

        List<User> users = userRepository.findAll();

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