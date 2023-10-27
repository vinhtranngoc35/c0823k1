import model.*;
import service.ProductService;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {

    static Scanner sc = new Scanner(System.in);
    static ProductService productService = new ProductService();
    public static void main(String[] args) {
        while (true){
            System.out.println("Menu:");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Them moi san pham");
            System.out.println("3. Cap nhat san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Sap xep");
            System.out.println("6. Tim kiem");
            System.out.println("7. Thoat");
            int choice = getNumber("Nhap vao lua chon");
            switch (choice){
                case 1 -> printProduct();
                case 2 -> createProduct();
                default -> System.exit(0);
            }
        }

    }

    private static void createProduct() {
        System.out.println("Nhap ten san pham");
        String name = sc.nextLine();
        System.out.println("Nhap mo ta san pham");
        String description = sc.nextLine();
        BigDecimal price = new BigDecimal(getNumber("Nhap gia"));
        productService.createProduct(new Product(0, name, price,description ));
    }

    private static void printProduct() {
        System.out.println("Id   | Name    | Description| Price");
        for (var product: productService.getProducts()) {
            System.out.printf("%x   | %s    | %s    | %f%n", product.getId(),product.getName(), product.getDescription(),product.getPrice());
        }
    }

    public static int  getNumber(String str) throws IndexOutOfBoundsException{

        System.out.println(str);
        int num;
        try{
            num = Integer.parseInt(sc.nextLine());
            return num;
        }catch (Exception e){
            System.out.println("Khong dung dinh dang");
            return getNumber(str);
        }



    }


}