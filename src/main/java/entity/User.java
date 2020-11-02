package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_USER")
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE T_USER SET name='DELETED',LAST_NAME = 'DELETED',E_MAIL = 'DELETED',USERNAME = 'DELETED',ENABLED = false WHERE USER_ID =?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "E_MAIL",unique = true)
    private String email;

    @Column(name = "USERNAME",unique = true)
    private String userName;

    @Column(name = "CREATED")
    private String createdAt;

    @Column(name = "ENABLED")
    private boolean enabled;

    @ToString.Exclude
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

    public User(String name, String lname, String email, String userName, String createdAt, boolean enabled, Discipline discipline, List<Task> taskList, Set<Role> roleSet) {
        this.name = name;
        this.lastName = lname;
        this.email = email;
        this.userName = userName;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.discipline = discipline;
        this.roles = roleSet;
        this.taskList = taskList;
    }

    public User(String name, String lastName, String eMail, String username) {
        this.name = name;
        this.lastName = lastName;
        this.email = eMail;
        this.userName = username;
    }

}
