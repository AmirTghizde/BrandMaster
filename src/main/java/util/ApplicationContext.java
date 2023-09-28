package util;

import connection.JdbcConnection;
import repository.*;
import service.*;

import java.sql.Connection;

public class ApplicationContext {
    private static final Connection CONNECTION;
    private static final BrandRepo BRAND_REPO;
    private static final CategoryRepo CATEGORY_REPO;
    private static final ProductRepo PRODUCT_REPO;
    private static final ShareholderRepo SHAREHOLDER_REPO ;
    private static final UserRepo USER_REPO;

    private static final BrandService BRAND_SERVICE;
    private static final CategoryService CATEGORY_SERVICE ;
    private static final ProductService PRODUCT_SERVICE;
    private static final ShareholderService SHAREHOLDER_SERVICE ;
    public static final UserService USER_SERVICE;

    static {
        CONNECTION = JdbcConnection.getConnection();

        BRAND_REPO = new BrandRepo(CONNECTION);
        BRAND_SERVICE = new BrandService(BRAND_REPO);

        CATEGORY_REPO=new CategoryRepo(CONNECTION);
        CATEGORY_SERVICE = new CategoryService(CATEGORY_REPO);

        PRODUCT_REPO = new ProductRepo(CONNECTION);
        PRODUCT_SERVICE= new ProductService(PRODUCT_REPO, CATEGORY_SERVICE, BRAND_SERVICE);

        SHAREHOLDER_REPO=new ShareholderRepo(CONNECTION);
        SHAREHOLDER_SERVICE=new ShareholderService(SHAREHOLDER_REPO,BRAND_SERVICE);

        USER_REPO=new UserRepo(CONNECTION);
        USER_SERVICE=new UserService(USER_REPO);
    }

    public static BrandService getBrandService(){
        return BRAND_SERVICE;
    }
    public static CategoryService getCategoryService(){
        return CATEGORY_SERVICE;
    }
    public static ProductService getProductService(){
        return PRODUCT_SERVICE;
    }
    public static ShareholderService getShareholderService(){
        return SHAREHOLDER_SERVICE;
    }
    public static UserService getUserService(){
        return USER_SERVICE;
    }
}
