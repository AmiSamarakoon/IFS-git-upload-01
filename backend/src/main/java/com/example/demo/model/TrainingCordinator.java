package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "training_cordinator")
public class TrainingCordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name="name")
    private String name;

    public TrainingCordinator() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
