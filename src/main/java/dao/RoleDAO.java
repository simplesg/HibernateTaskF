package dao;

import entity.Role;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO extends RepositoryDao<Role> {

    public List<Role> getRoleByName(String name) throws SQLException;
}
