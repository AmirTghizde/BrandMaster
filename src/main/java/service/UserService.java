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

    public boolean register(User user) throws SQLException {
            if (userRepo.isUsernameExists(user.getUsername())) {
                System.out.println("\n\n\n\n\n\n\n\n!Username already exists");
                return false;
            } else if (userRepo.isEmailExists(user.getEmail())) {
                System.out.println("\n\n\n\n\n\n\n\n!Email already exists");
                return false;
            } else {
                userRepo.save(user);
                return true;
            }
    }


    public User login(String username) throws SQLException {

        User user = userRepo.login(username);
        return user;
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
