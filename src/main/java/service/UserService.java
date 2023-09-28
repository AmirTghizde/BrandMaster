package service;

import model.User;
import repository.UserRepo;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class UserService {
    private final UserRepo userRepo;
    Scanner sc = new Scanner(System.in);

    public UserService(UserRepo userRepo)  {
        this.userRepo = userRepo;
    }

    public void register() throws SQLException {
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
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        User user = userRepo.login(username);
        if (user != null && user.getPassword().equals(password)) {//if the user is not null and the password is the same...
            System.out.println("Successfully logged in :D");
        } else System.out.println("Wrong username or password");
    }

    public void changeName() throws SQLException {
        //TODO change this part and make it dynamic so the user only sends the new name
        System.out.print("Enter your id: ");//temporary
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the new name: ");
        String name = sc.nextLine();

        userRepo.editName(id, name);
    }

    public void changeUsername() throws SQLException {
        System.out.print("Enter your current username: ");
        String username = sc.nextLine();
        System.out.print("Enter the new username: ");
        String newUsername = sc.nextLine();

        if (userRepo.isUsernameExists(newUsername)) {
            System.out.println("!Username is taken");
        } else userRepo.editUsername(username, newUsername);
    }

    public void changeEmail() throws SQLException {
        System.out.print("Enter your current email: ");
        String email = sc.nextLine();
        System.out.print("Enter the new email: ");
        String newEmail = sc.nextLine();

        if (userRepo.isUsernameExists(newEmail)) {
            System.out.println("!Email is already being used");
        } else userRepo.editEmail(email, newEmail);
    }

    public void changePassword() throws SQLException {
        //TODO change this part and make it dynamic so the user only sends the new password
        boolean repeat = true;
        System.out.print("Enter your id: ");//temporary
        int id = sc.nextInt();
        sc.nextLine();
        while (repeat) {
            System.out.print("Enter the new password: ");
            String password = sc.nextLine();
            System.out.print("Enter the new password again: ");
            String passwordRepeat = sc.nextLine();
            if (Objects.equals(password, passwordRepeat)) {
                repeat=false;
                userRepo.editPassword(id, password);
            } else System.out.println("!Repeated password doesn't match");
        }
    }
    public void deleteAccount() throws SQLException {
        //TODO change this part and make it dynamic so the user only hits delete
        System.out.print("Enter your id: ");//temporary
        int id = sc.nextInt();
        sc.nextLine();
        userRepo.delete(id);
    }
}
