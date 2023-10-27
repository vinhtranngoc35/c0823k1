package service;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final List<Product> products = new ArrayList<>();

    private static int currentId = 0;

    static {
        products.add(new Product(++currentId, "Cafe", new BigDecimal(6_000), "Ngon"));
        products.add(new Product(++currentId, "Bo huc", new BigDecimal(15_000), "Ngon"));
        products.add(new Product(++currentId, "Sting", new BigDecimal(10_000), "Ngon"));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void createProduct(Product product){
        product.setId(++currentId);
        products.add(product);
    }
}