package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coordinator")
public class Coordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id")
    private long coordinatorId;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "coordinator", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<TrainingSession> trainingSessions;

    public Coordinator() { }

    public Coordinator(long coordinatorId, String name) {
        this.coordinatorId = coordinatorId;
        this.name = name;
    }

    public long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }
}
