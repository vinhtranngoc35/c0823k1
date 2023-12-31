package view;

import model.Product;
import service.CategoryService;
import service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

import static ultis.AppUtil.*;

// lấy data từ người dùng.
public class ProductView {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();
    static CategoryService categoryService = new CategoryService();

    public static void printMenu() {
        boolean flag =true;
        while (flag) {
            System.out.println("Menu:");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them moi san pham");
            System.out.println("3. Cap nhat san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Sap xep");
            System.out.println("6. Tim kiem");
            System.out.println("7. Thoat");
            int choice = getNumber("Nhap vao lua chon", 1, 7);
            switch (choice) {
                case 1 -> printProduct();
                case 2 -> createProduct();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                default -> flag = false;
            }
        }
    }

    private static void deleteProduct() {
        printProduct();
        boolean isSuccess = productService.deleteProduct(getNumber("Nhap Id muon xoa"));
        if(isSuccess){
            System.out.println("xoa Thanh cong");
        }else {
            System.out.println("Khong tim thay Id");
        }
    }

    private static void updateProduct() {
        printProduct();
        boolean isSuccess = productService.updateProduct(new Product(
                getNumber("Nhap Id muon sua")
                , getString("Nhap ten muon sua")
                , new BigDecimal(getNumber("Nhap gia"))
                , getString("Nhap description")
                , getNumber("Nhap category")
                ,  getDate("Nhap ngay nhap hang")
        ));
        if(isSuccess){
            System.out.println("Cap nhat Thanh cong");
        }else {
            System.out.println("Khong tim thay Id");
        }
    }

    public static void createProduct() {
        productService.createProduct(new Product(0, getString("Nhap ten san pham"),
                new BigDecimal(getNumber("Nhap gia")), getString("Nhap mo ta san pham"),
                getCategoryId("Nhap category id"), getDate("Nhap ngay nhap hang")));
    }

    public static void printProduct() {
        System.out.println("Id   | Name    | Description| Price     | Category");
        for (var product : productService.getProducts(1)) {
            System.out.printf("%x   | %s    | %s    | %f     | %s%n", product.getId(), product.getName(), product.getDescription(), product.getPrice(),product.getCategory().getName());
        }
    }




    public static int getCategoryId(String str){
        System.out.println("Danh sach category");
        for (var category: categoryService.findAll()) {
            System.out.println(category.getId() + ". " + category.getName());
        }
        return getNumber(str);
    }


}