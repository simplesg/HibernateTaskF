package entity;

import entity.enums.DisciplineType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_id")
    private int id;

    @OneToOne
    @JoinColumn(name="HeadOfDiscipline",referencedColumnName = "USER_ID")
    private User headOfDiscipline;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private DisciplineType disciplineType;

    @OneToMany(mappedBy = "discipline",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    public Discipline() {

    }

    public Discipline(DisciplineType am) {
        this.disciplineType = am;
    }

    public User getUser() {
        return headOfDiscipline;
    }

    public void setUser(User user) {
        this.headOfDiscipline = user;
    }

    public DisciplineType getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(DisciplineType disciplineType) {
        this.disciplineType = disciplineType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                ", disciplineType=" + disciplineType +
                ", disciplineHead=" + headOfDiscipline +
                '}';
    }
}
