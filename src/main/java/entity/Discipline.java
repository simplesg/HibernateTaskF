package entity;

import entity.enums.DisciplineType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_discipline")
@Data
@NoArgsConstructor

public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_id")
    private int id;

    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name="headOfDiscipline",referencedColumnName = "USER_ID")
    private User headOfDiscipline;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private DisciplineType disciplineType;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "discipline",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    public Discipline(DisciplineType disciplineType){
        this.disciplineType = disciplineType;
    }
}
