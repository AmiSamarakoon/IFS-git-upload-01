package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        }),
        @UniqueConstraint(columnNames = {
                "user_name"
        })
})
public class Trainer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainerId;

    @NotBlank
    @Size(min=3, max = 50)
    @Column(name="name")
    private String name;

    @NotBlank
    @Size(min=3, max = 50)
    @Column(name="user_name")
    private String username;

    @NotBlank
    @Column(name = "type")
    private String type;

    @ElementCollection
    @CollectionTable(
            name = "Qualification",
            joinColumns = @JoinColumn(name = "trainerId")
    )
    private List<String> qualifications;

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name="email")
    private String email;

    @NotBlank
    @Column(name="contactNo")
    private String contactNo;



    public Trainer() {
    }

    public Trainer(@NotBlank @Size(min = 3, max = 50) String name, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank String type, List<String> qualifications, @NotBlank @Size(max = 50) @Email String email, @NotBlank String contactNo) {
        this.name = name;
        this.username = username;
        this.type = type;
        this.qualifications=qualifications;
        this.email = email;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "training_session_trainer",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "training_session_id")
    )

    @JsonIgnoreProperties("trainers")
    private List<TrainingSession> trainingSessions;




    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }



    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }


    public void add(TrainingSession tempSession) {

        if(trainingSessions ==null) {

            trainingSessions = new ArrayList<TrainingSession>();

        }

        trainingSessions.add(tempSession);


    }



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "trainer" ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JsonIgnoreProperties("trainer")
    private List<Leave> leaveApplications;


    public List<Leave> getLeaveApplications() {
        return leaveApplications;
    }

    public void setLeaveApplications(List<Leave> leaveApplications) {
        this.leaveApplications = leaveApplications;
    }






}
