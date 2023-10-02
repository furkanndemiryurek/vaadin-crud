package core.service;

import core.dao.impl.CategoryDaoImpl;
import core.entity.Category;

import java.util.List;

public class CategoryService {
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    public void add(Category category){
        categoryDao.add(category);
    }
    public List<Category> findAll(){
        return categoryDao.findAll();
    }
    public Category findById(int id){ return categoryDao.findById(id); }
    public Category findByName(String name){ return categoryDao.findByName(name); }
    public void deleteById(int id){
        categoryDao.deleleteById(id);
    }
    public void update(Category category){
        categoryDao.update(category);
    }
}
