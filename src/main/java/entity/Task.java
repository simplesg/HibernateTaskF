package entity;

import entity.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String startDate;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Task(String name, String description, String startDate, String dueDate, Status status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }


}
