package core.dao;

import java.util.List;

public interface GenericDao <T>{
    void add(T entity);
    List<T> findAll();
    T findById(int id);
    T findByName(String name);
    void deleleteById(int id);
    void update(T entity);

}
