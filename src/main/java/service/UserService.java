package service;

import model.User;
import repository.UserRepo;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    UserRepo userRepo = new UserRepo();

    public UserService() throws SQLException {
    }

    public void register() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            System.out.print("Please enter your name: ");
            String name = sc.nextLine();
            System.out.print("Please enter your username: ");
            String username = sc.nextLine();
            System.out.print("please enter your email: ");
            String email = sc.nextLine();
            System.out.print("please enter your password: ");
            String password = sc.nextLine();

            if (userRepo.isUsernameExists(username)) {
                System.out.println("!Username already exists");
            } else if (userRepo.isEmailExists(email)) {
                System.out.println("!Email already exists");
            } else {
                repeat = false;
                User user = new User();
                user.setName(name);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);

                userRepo.save(user);
            }
        }
    }

    public void login() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        User user = userRepo.login(username);
        if (user!=null&& user.getPassword().equals(password)){//if the user is not null and the password is the same...
            System.out.println("Successfully logged in :D");
        }else System.out.println("Wrong username/password");
    }
}
