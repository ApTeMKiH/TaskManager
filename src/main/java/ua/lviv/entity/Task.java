package ua.lviv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * Created by PC-6 on 25.04.2017.
 */
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    @Column(length = 1000)
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JsonIgnore
    @OneToOne
    private User userTo;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    @Cascade(CascadeType.DELETE)
    private List<Feedback> feedbackList;

    public Task() {
    }

    public Task(String subject, String text, Date date) {
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }
}
