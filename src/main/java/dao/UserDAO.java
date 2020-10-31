package dao;

import entity.User;
import entity.enums.DisciplineType;
import entity.enums.Status;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void add(User user) throws SQLException;

    List<User> getAll() throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;

    List<User> getUserByRole(String role) throws SQLException;

    List<User> getUsersByDiscipline(DisciplineType disciplineType) throws SQLException;

    List<User> getUsersByTaskStatus(Status status) throws SQLException;

    List<User> getUserById(int id) throws SQLException;

}
