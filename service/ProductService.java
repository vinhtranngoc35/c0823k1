package service;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//
// => lấy thông tin từ người dùng
// parse data => object model
// => add vào list data của mình
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

    public void createProduct(Product product) {
        product.setId(++currentId);
        products.add(product);
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
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
}