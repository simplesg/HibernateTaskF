package entity;

import com.sun.xml.internal.bind.v2.model.core.ID;
import entity.enums.Status;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_task")
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

    public Task() {
    }

    public Task(String name, String description, String startDate, String dueDate, Status status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[ " + "name:" + name + "dueDate: " + dueDate + "status: " + status + "]";
    }
}
