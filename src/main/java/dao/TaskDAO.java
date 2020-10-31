package dao;

import entity.Role;
import entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void add(Task task) throws SQLException;

    List<Task> getAll() throws SQLException;

    void update(Task role) throws SQLException;

    void delete(Task role) throws SQLException;
}
