package core.service;

import core.dao.impl.ProductDaoImpl;
import core.entity.Product;

import java.util.List;

public class ProductService {
    ProductDaoImpl productDao = new ProductDaoImpl();

    public void addProduct(Product product){
        productDao.add(product);
    }

    public List<Product> findAll(){
        return productDao.findAll();
    }

    public void deleteById(int id){
        productDao.deleleteById(id);
    }
    public Product findById(int id) {
        return productDao.findById(id);
    }

    public  Product findByName(String name){
        return productDao.findByName(name);
    }

    public void update(Product product) { productDao.update(product); }
}
