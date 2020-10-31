package entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_USER")
@SQLDelete(sql = "UPDATE T_USER SET name='DELETED',LAST_NAME = 'DELETED',E_MAIL = 'DELETED',USERNAME = 'DELETED' WHERE USER_ID =?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "E_MAIL")
    private String eMail;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne(mappedBy = "headOfDiscipline")
    private Discipline headOfDiscipline;

    @ManyToOne(fetch = FetchType.LAZY)
    private Discipline discipline;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "u_id"),
            inverseJoinColumns = @JoinColumn(name = "r_id")
    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private List<Task> taskList;

    public User(String name, String lastName, String eMail, String username, Discipline discipline, List<Task> tasks,Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.eMail = eMail;
        this.username = username;
        this.discipline = discipline;
        this.roles = roles;
        this.taskList = tasks;
    }

    public User() {

    }

    public User(String name, String lastName, String eMail, String username) {
        this.name = name;
        this.lastName = lastName;
        this.eMail = eMail;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "User[ " + " fName: " + name + " lName: " + lastName +
                " e-mail: " + eMail + " username:" + " discipline: " + discipline.getDisciplineType() +
                " roles: " + roles + " tasks: " + taskList;
    }
}
