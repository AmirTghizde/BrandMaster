package service;

import model.Brand;
import repository.BrandRepo;
import util.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class BrandService {
    private final BrandRepo brandRepo;
    Scanner sc = new Scanner(System.in);

    public BrandService(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public void add() throws SQLException {
        boolean repeat = true;
        boolean validWebsite = false;
        while (repeat && !validWebsite) {
            System.out.print("Enter brand name: ");
            String name = sc.nextLine();
            System.out.print("Enter brand website: ");
            String website = sc.nextLine();
            if (Validation.isValidWebsite(website)) {
                validWebsite = true;
                System.out.print("Enter brand description: ");
                String description = sc.nextLine();

                if (brandRepo.isNameExists(name)) {
                    System.out.println("!Brand name already exists");
                } else {
                    repeat = false;
                    brandRepo.save(new Brand(name, website, description));
                }
            } else System.out.println("Invalid website");
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
        if (Validation.isValidWebsite(website)) {
            brandRepo.editWebsite(id, website);
        }
        else System.out.println("Invalid website");
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

    public Brand load(int id) throws SQLException {
        return brandRepo.load(id);
    }

    public void show() throws SQLException {
        System.out.println("\t\t**BRANDS**");
        brandRepo.showBrands();
    }
}
