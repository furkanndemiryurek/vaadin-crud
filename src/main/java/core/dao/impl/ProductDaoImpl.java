package core.dao.impl;

import core.dao.ProductDao;
import core.dao.utils.HibernateUtil;
import core.entity.Category;
import core.entity.Product;
import core.service.CategoryService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    HibernateUtil hibernateUtil = new HibernateUtil();

    @Override
    public void add(Product entity) {
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<Product>();
        try {
            Session session = hibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "From Product";
            Query query = session.createQuery(hql);
            products = query.list();
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            product = session.find(Product.class, id);
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return product;
    }

    @Override
    public Product findByName(String name) {
        Product product = new Product();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Product WHERE name = :data";
            Query query = session.createQuery(hql);
            query.setParameter("data",name);
            product = (Product) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return product;
    }

    @Override
    public void deleleteById(int id) {
        Product product = new Product();
        try {
            Session session = hibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            product = (Product) session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update(Product entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Product selectedProduct = (Product) session.get(Product.class, entity.getId());
            CategoryService categoryService = new CategoryService();
            Category tempCategory = categoryService.findByName(entity.getCategory().getCategoryName());
            selectedProduct.setCategory(tempCategory);
            selectedProduct.setName(entity.getName());
            session.update(selectedProduct);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
