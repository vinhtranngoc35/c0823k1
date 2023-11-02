package service;

import model.Category;
import model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static List<Category> categories = new ArrayList<>();

    private static int currentId = 0;

    static {
        readData();
    }
    public Category findById(int id){
        return categories.stream().filter(e -> e.getId() == id).findFirst().orElse(new Category(1, "Cafe"));
    }
    public List<Category> findAll(){
        return categories;
    }
    private static void readData() {
        try{
            File fileWriter = new File("data/category.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                Category category = new Category(
                        Integer.parseInt(data[0]),
                        data[1]
                );
                categories.add(category);

                line = reader.readLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}