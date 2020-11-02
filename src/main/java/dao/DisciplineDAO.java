package dao;

import entity.Discipline;
import entity.Role;
import entity.enums.DisciplineType;

import java.sql.SQLException;
import java.util.List;

public interface DisciplineDAO extends RepositoryDao<Discipline>{

    Discipline getDiscipline(DisciplineType disciplineType) throws SQLException;

}
