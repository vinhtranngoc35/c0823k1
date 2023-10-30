package view;

import model.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

// lấy data từ người dùng.
public class ProductView {
    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();

    public static void printMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them moi san pham");
            System.out.println("3. Cap nhat san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Sap xep");
            System.out.println("6. Tim kiem");
            System.out.println("7. Thoat");
            int choice = getNumber("Nhap vao lua chon");
            switch (choice) {
                case 1 -> printProduct();
                case 2 -> createProduct();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                default -> System.exit(0);
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
        ));
        if(isSuccess){
            System.out.println("Cap nhat Thanh cong");
        }else {
            System.out.println("Khong tim thay Id");
        }
    }

    public static void createProduct() {
        productService.createProduct(new Product(0, getString("Nhap ten san pham"),
                new BigDecimal(getNumber("Nhap gia")), getString("Nhap mo ta san pham")));
    }

    public static void printProduct() {
        System.out.println("Id   | Name    | Description| Price");
        for (var product : productService.getProducts()) {
            System.out.printf("%x   | %s    | %s    | %f%n", product.getId(), product.getName(), product.getDescription(), product.getPrice());
        }
    }

    public static int getNumber(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            return num;
        } catch (Exception e) {
            System.out.println("Khong dung dinh dang");
            return getNumber(str);
        }

    }

    public static String getString(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }
}