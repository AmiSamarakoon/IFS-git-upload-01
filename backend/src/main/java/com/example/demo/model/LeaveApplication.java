package com.example.demo.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "leave_application")
public class LeaveApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long l_id;



    @Column(name = "title")
    private  int title;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "duration")
    private  int duration;

    @Column(name = "type")
    private  int type;

    @Column(name = "description")
    private  int description;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "trainerId")
    private Trainer trainer;


    public LeaveApplication() {

    }



    public LeaveApplication(Date date, int duration, Trainer trainer) {
        this.date = date;
        this.duration = duration;
        this.trainer = trainer;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }


    public long getL_id() {
        return l_id;
    }

    public void setL_id(long l_id) {
        this.l_id = l_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
