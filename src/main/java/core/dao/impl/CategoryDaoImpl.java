package core.dao.impl;

import core.dao.CategoryDao;
import core.dao.utils.HibernateUtil;
import core.entity.Category;
import core.entity.Product;
import core.service.CategoryService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void add(Category entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<Category>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "From Category";
            Query query = session.createQuery(hql);
            categories = query.list();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        Category category = new Category();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            category = session.get(Category.class, id);
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return category;
    }

    @Override
    public void deleleteById(int id) {
        Category category = new Category();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            category = (Category) session.get(Category.class, id);
            session.delete(category);
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update(Category entity) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Category selectedCategory = (Category) session.get(Category.class, entity.getId());
            CategoryService categoryService = new CategoryService();
            selectedCategory.setCategoryName(entity.getCategoryName());
            session.update(selectedCategory);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Category findByName(String name) {
        Category category = new Category();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "From Category Where categoryName =:data";
            Query query = session.createQuery(hql);
            query.setParameter("data",name);
            category = (Category) query.getSingleResult();
            transaction.commit();
            session.close();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return category;
    }
}
