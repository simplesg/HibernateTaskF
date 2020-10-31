package dao;

import entity.Role;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {


    void add(Role role) throws SQLException;

    List<Role> getAll() throws SQLException;

    void update(Role role) throws SQLException;

    void delete(Role role) throws SQLException;

    public List<Role> getRoleByName(String name) throws SQLException;
}
