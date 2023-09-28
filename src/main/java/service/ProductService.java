package service;

import model.Product;
import repository.ProductRepo;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductService {
    //TODO don't forget apllication context and validation and menu :P
    private final ProductRepo productRepo;
    private final CategoryService categoryService;
    private final  BrandService brandService;


    Scanner sc = new Scanner(System.in);

    public ProductService(ProductRepo productRepo, CategoryService categoryService, BrandService brandService)  {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }
    public void add() throws SQLException {
        System.out.print("Enter Product name: ");
        String name = sc.nextLine();
        System.out.print("Enter Creation date: ");
        String creatDAte = sc.nextLine();
        System.out.print("Enter Category id: ");
        int categoryID=sc.nextInt();
        System.out.print("Enter Brand id: ");
        int brandID=sc.nextInt();

        Product product = new Product(name,creatDAte);
        if (categoryService.load(categoryID)==null){
            System.out.println("!Category not found");}
        else if (brandService.load(brandID)==null){
            System.out.println("!Brand not found");
        }else {
            product.setBrand(brandService.load(brandID));
            product.setCategory(categoryService.load(categoryID));
            productRepo.save(product);
        }
    }
    public void editName() throws SQLException {
        System.out.print("Enter id: ");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String  name = sc.nextLine();

        productRepo.editName(id,name);
    }
    public void editCreateDate() throws SQLException {
        System.out.print("Enter id: ");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new creation date: ");
        String  creationDate = sc.nextLine();

        productRepo.editCreateDate(id,creationDate);
    }
    public void editCategoryID() throws SQLException {
        System.out.print("Enter id: ");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new category id: ");
        int categoryID = sc.nextInt();
        if (categoryService.load(categoryID)!=null){
            productRepo.editCategory_id(id,categoryID);
        }else System.out.println("!Category not found");


    }public void editBrandID() throws SQLException {
        System.out.print("Enter id: ");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new brand id: ");
        int brandID = sc.nextInt();
        if (brandService.load(brandID)!=null){
            productRepo.editBrand_id(id,brandID);
        }else System.out.println("!Brand not found");
    }
    public void delete() throws SQLException {
        System.out.print("Enter id: ");
        int id=sc.nextInt();
        productRepo.delete(id);
    }
}
