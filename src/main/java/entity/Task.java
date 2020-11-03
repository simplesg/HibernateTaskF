package entity;

import entity.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_task")
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "dueDate")
    private Date dueDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Task(String name, String description, Date startDate, Date dueDate, Status status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }


}
