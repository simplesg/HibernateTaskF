package service;

import dao.RoleDAO;
import entity.Role;
import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class RoleService extends SessionUtil implements RoleDAO {
    @Override
    public void addToDatabase(Role role) throws SQLException {
        //open session
        openTransactionSession();

        Session session = getSession();
        session.save(role);

        //close session
        closeTransactionSession();
    }

    @Override
    public List<Role> getAllFromDatabase() throws SQLException {
        List<Role> userList;
        openTransactionSession();

        Session session = getSession();
        Query query  = session.createQuery("from "+ Role.class.getCanonicalName());
        userList = query.list();

        return userList;
    }

    @Override
    public List<Role> getRoleByName(String name) throws SQLException {
        List<Role> userList;
        openTransactionSession();

        Session session = getSession();
        Query query  = session.createQuery("from Role r where r.name=:name");
        query.setParameter("name",name);
        userList = query.list();

        return userList;
    }

    @Override
    public void update(Role role) throws SQLException {

        //open session
        openTransactionSession();

        Session session = getSession();
        session.update(role);

        //close session
        closeTransactionSession();
    }

    @Override
    public void delete(Role role) throws SQLException {

    }
}
