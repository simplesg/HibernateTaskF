package service;

import dao.TaskDAO;
import entity.Task;
import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class TaskService extends SessionUtil implements TaskDAO {

    @Override
    public void addToDatabase(Task task) throws SQLException {
        //open session
        openTransactionSession();

        Session session = getSession();
        session.save(task);

        //close session
        closeTransactionSession();
    }

    @Override
    public List<Task> getAllFromDatabase() throws SQLException {
        List<Task> userList;
        openTransactionSession();

        Session session = getSession();
        Query query  = session.createQuery("from "+ Task.class.getCanonicalName());
        userList = query.list();

        return userList;
    }

    @Override
    public void update(Task task) throws SQLException {
        //open session
        openTransactionSession();

        Session session = getSession();
        session.update(task);

        //close session
        closeTransactionSession();
    }

    @Override
    public void delete(Task task) throws SQLException {

    }
}
