package dao;

import entity.Role;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryDao<T> {

    void addToDatabase(T t) throws SQLException;

    List<T> getAllFromDatabase() throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;



}
