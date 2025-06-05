package org.example.platzi.repository;

import java.sql.SQLException;
import java.util.List;

//<T> Tipo generico para ser implementado en diferentes clases
public interface Repository <T>{

    //Metodos

    List<T> findAll() throws SQLException;

    T getById(Integer id) throws SQLException;

    void save(T t);

    void delete(Integer id);
}
