package service;

import model.Brand;
import repository.BrandRepo;

import java.sql.SQLException;
import java.util.Scanner;

public class BrandService {
    BrandRepo brandRepo = new BrandRepo();
    Scanner sc = new Scanner(System.in);

    public BrandService() throws SQLException {
    }

    public void add() throws SQLException {
        boolean repeat = true;
        while (repeat) {
            System.out.print("Enter brand name: ");
            String name = sc.nextLine();
            System.out.print("Enter brand website: ");
            String website = sc.nextLine();
            System.out.print("Enter brand description: ");
            String description = sc.nextLine();

            if (brandRepo.isNameExists(name)) {
                System.out.println("!Brand name already exists");
            } else {
                repeat=false;
                brandRepo.save(new Brand(name, website, description));
            }
        }
    }

    public void editName() throws SQLException {
        System.out.print("Enter brand id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        if (brandRepo.isNameExists(name)) {
            System.out.println("!Name is already taken");
        } else brandRepo.editName(id, name);
    }

    public void editWebsite() throws SQLException {
        System.out.print("Enter brand id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new website address: ");
        String website = sc.nextLine();
        brandRepo.editWebsite(id, website);
    }

    public void editDescription() throws SQLException {
        System.out.print("Enter brand id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new description: ");
        String description = sc.nextLine();
        brandRepo.editDescription(id, description);
    }

    public void delete() throws SQLException {
        System.out.print("Enter brand id: ");
        int id = sc.nextInt();
        brandRepo.delete(id);
    }

    public void show() throws SQLException {
        System.out.println("\t\t**BRANDS**");
        brandRepo.showBrands();
    }
}
