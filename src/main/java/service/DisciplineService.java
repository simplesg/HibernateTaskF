package service;

import dao.DisciplineDAO;
import entity.Discipline;
import entity.User;
import entity.enums.DisciplineType;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class DisciplineService extends SessionUtil implements DisciplineDAO {
    @Override
    public void add(Discipline discipline) throws SQLException {
        //open session
        openTransactionSession();

        Session session = getSession();
        session.save(discipline);

        //close session
        closeTransactionSession();
    }

    @Override
    public List<Discipline> getAll() throws SQLException {
        return null;
    }



    @Override
    public void update(Discipline discipline) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.update(discipline);

        //close session
        closeTransactionSession();
    }

    @Override
    public void delete(Discipline discipline) throws SQLException {

    }

    @Override
    public Discipline getDiscipline(DisciplineType disciplineType) throws SQLException {
        //open session
        List<Discipline> discipline;
        openTransactionSession();

        Session session = getSession();
        Query query  = session.createQuery("from Discipline d where d.disciplineType=:disciplineType");
        query.setParameter("disciplineType",disciplineType);
        discipline = query.list();

        return discipline.get(0);
    }
}
