package service;

import model.Category;
import repository.CategoryRepo;

import java.sql.SQLException;
import java.util.Scanner;

public class CategoryService {
    private final CategoryRepo categoryRepo;
    Scanner sc = new Scanner(System.in);

    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public void add() throws SQLException {
        System.out.print("Enter Category Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Category Description: ");
        String description = sc.nextLine();

        if (categoryRepo.isNameExists(name)) {
            System.out.println("!Category name already exists");
        } else {
            categoryRepo.save(new Category(name,description));
        }
    }
    public void editName() throws SQLException {
        boolean repeat=true;
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        while (repeat){
        System.out.print("Enter the new name: ");
        String name=sc.nextLine();
        if(!categoryRepo.isNameExists(name)){
            repeat=false;
            categoryRepo.editName(id,name);
        }else System.out.println("!Name already exists");
        }
    }
    public void editDescription() throws SQLException {
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the new description: ");
        String name=sc.nextLine();
            categoryRepo.editDescription(id,name);
    }

    public void delete() throws SQLException {
        System.out.print("Enter id: ");
        int id=sc.nextInt();
        categoryRepo.delete(id);
    }
    public Category load(int id) throws SQLException {
        return categoryRepo.load(id);
    }
    public void show() throws SQLException {
        System.out.println("\t\t***Categories**");
        categoryRepo.showCategory();
    }
}
