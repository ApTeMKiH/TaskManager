package ua.lviv.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private String mark;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    public Feedback() {
    }

    public Feedback(String text, String mark, Date date) {
        this.text = text;
        this.mark = mark;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "text='" + text + '\'' +
                ", mark='" + mark + '\'' +
                ", date=" + date +
                '}';
    }
}
