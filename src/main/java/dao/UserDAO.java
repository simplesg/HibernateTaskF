package dao;

import entity.Role;
import entity.User;
import entity.enums.DisciplineType;
import entity.enums.Status;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends RepositoryDao<User>{

    List<User> getUserByRole(String role) throws SQLException;

    List<User> getUsersByDiscipline(DisciplineType disciplineType) throws SQLException;

    List<User> getUsersByTaskStatus(Status status) throws SQLException;

    List<User> getUserById(int id) throws SQLException;

}
