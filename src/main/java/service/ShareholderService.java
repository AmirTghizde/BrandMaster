package service;

import model.Brand;
import model.Shareholder;
import org.postgresql.ssl.SingleCertValidatingFactory;
import repository.ShareholderRepo;
import util.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class ShareholderService {
    private final ShareholderRepo shareholderRepo;
    private final BrandService brandService;

    Scanner sc = new Scanner(System.in);

    public ShareholderService(ShareholderRepo shareholderRepo, BrandService brandService) {
        this.shareholderRepo = shareholderRepo;
        this.brandService = brandService;
    }

    public void add() throws SQLException {
        boolean repeat = true;
        boolean isValidPhonenumber=false;
        boolean isValidNationalCode=false;
        while (repeat&& !isValidPhonenumber&& !isValidNationalCode) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = sc.nextLine();
            if (Validation.isValidPhoneNumber(phoneNumber)) {
                isValidPhonenumber=true;
                System.out.print("Enter national code: ");
                String nationalCode = sc.nextLine();
                if (Validation.isValidNationalCode(nationalCode)) {
                    if (shareholderRepo.isNationalCodeExists(nationalCode)) {
                        System.out.println("!National code already exists");
                    } else {
                        isValidNationalCode=true;
                        System.out.print("Enter brand id: ");
                        int brandID = sc.nextInt();
                        if (brandService.load(brandID) != null) {
                            Brand[] brands = new Brand[]{brandService.load(brandID)};
                            Shareholder shareholder = new Shareholder(name, phoneNumber, nationalCode, brands);
                            shareholderRepo.save(shareholder, brandID);
                            repeat=false;
                        } else System.out.println("!Brand not found");
                    }
                } else System.out.println("!Invalid national code");
            } else System.out.println("!invalid phone number");

        }
    }

    public void addMoreBrands() throws SQLException {
        System.out.print("Enter shareholder id: ");
        int shareholderID = sc.nextInt();
        System.out.print("Enter Brand id: ");
        int brandID = sc.nextInt();
        if (shareholderRepo.isShareHolderExists(shareholderID)) {
            if (brandService.load(brandID) != null) {
                try {
                    shareholderRepo.saveToShareholder_Brand(shareholderID, brandID);
                } catch (Exception e) {
                    System.out.println("!Shareholder already has that");
                }
            } else System.out.println("!Brand not found");
        } else System.out.println("!Shareholder not Found");
    }

    public void editName() throws SQLException {
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        shareholderRepo.editName(id, name);
    }

    public void editPhoneNumber() throws SQLException {
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        shareholderRepo.editPhoneNumber(id, phoneNumber);
    }

    public void editNationalCode() throws SQLException {
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter nationalCode: ");
        String nationalCode = sc.nextLine();
        if (!shareholderRepo.isNationalCodeExists(nationalCode)) {
            shareholderRepo.editNationalCode(id, nationalCode);
        } else {
            System.out.println("!NationalCode already exists");
        }
    }

    public void delete() throws SQLException {
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        shareholderRepo.delete(id);
    }

    public void deleteShare() throws SQLException {
        System.out.print("Enter brand id: ");
        int id = sc.nextInt();
        shareholderRepo.deleteFromShareholder_BrandByBrandID(id);
    }
}


