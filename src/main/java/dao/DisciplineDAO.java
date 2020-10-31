package dao;

import entity.Discipline;
import entity.Role;
import entity.enums.DisciplineType;

import java.sql.SQLException;
import java.util.List;

public interface DisciplineDAO {
    void add(Discipline discipline) throws SQLException;

    List<Discipline> getAll() throws SQLException;

    void update(Discipline discipline) throws SQLException;

    void delete(Discipline discipline) throws SQLException;

    Discipline getDiscipline(DisciplineType disciplineType) throws SQLException;

}
