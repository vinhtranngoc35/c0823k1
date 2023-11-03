package service;

import model.Product;
import model.Unit;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//
// => lấy thông tin từ người dùng
// parse data => object model
// => add vào list data của mình
public class ProductService {
    private static List<Product> products = new ArrayList<>();

    private static CategoryService categoryService = new CategoryService();



    private static int currentId = 0;

    static {
        readData();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void createProduct(Product product) {
        product.setId(++currentId);
        product.setCategory(categoryService.findById(product.getCategoryId()));
        products.add(product);
        writeFile();
    }

    //{id: 1, Name: Cafe Expresso, Description: Cafe rang xay, 12_000}

    public boolean updateProduct(Product product) {
        for (var item : products) {
            if (item.getId() == product.getId()) {
                item.setName(product.getName());
                item.setDescription(product.getDescription());
                item.setPrice(product.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        var sizeOld = products.size();
        products = products.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
        writeFile();
        return sizeOld != products.size();
    }
    public List<Product> findProductByName(String name){
        return products.stream().filter(e -> e.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
    public List<Product> getProducts(int choice) {
        if(choice == 1){
            products.sort(Comparator.comparing(Product::getPrice));
        }
        if(choice == 2){
            products.sort(Comparator.comparing(Product::getName));
        }
        return products;
    }

    public Product findById(int id) {
       return products.stream().filter(e -> e.getId() == id).findFirst().orElse(new Product());
    }

    private static void readData(){
        try{
            File fileWriter = new File("data/data.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                Product product = new Product(
                        Integer.parseInt(data[0]),
                        data[1],
                        new BigDecimal(data[2].trim()),
                        data[3],
                        Integer.parseInt(data[4]),
                        Date.valueOf(data[5])
                );
                product.setUnit(Unit.valueOf(data[6]));
                product.setCategory(categoryService.findById(product.getCategoryId()));
                products.add(product);

                line = reader.readLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/data.txt"));
            for (var product :products) {
                writer.write(product.toString());
                writer.newLine();
            }


            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}