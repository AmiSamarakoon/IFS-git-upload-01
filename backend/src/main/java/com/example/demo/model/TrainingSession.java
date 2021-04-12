package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="training_session")
public class TrainingSession {


    @javax.persistence.Transient
    public long [] trainerids;


    @javax.persistence.Transient
    public String[] vmIds;

    public String[] getVmIds() {
        return vmIds;
    }

    public void setVmIds(String[] vmIds) {
        this.vmIds = vmIds;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "max_participants")
    private int maxParticipants;

    @Column(name = "ifs_application_version")
    private String ifsApplicationVersion;

    @Column(name = "buffer_time")
    private int bufferTime;

    @Column(name = "manager_comments")
    private String managerComment;

    @Column(name = "delivery_method")
    private String deliveryMethod;

    @Column(name = "type")
    private String type;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingRoom_id", referencedColumnName = "id")
    private TrainingRoom trainingRoom;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingCordinator_id", referencedColumnName = "id")
    private TrainingCordinator trainingCordinator;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TrainingSession() {
    }

    public TrainingSession(String sessionName,
                           Date startDate,
                           int duration,
                           int maxParticipants,
                           String ifsApplicationVersion,
                           int bufferTime,
                           String managerComment,
                           String deliveryMethod,
                           String type) {
        super();
        this.sessionName = sessionName;
        this.startDate = startDate;
        this.duration = duration;
        this.maxParticipants = maxParticipants;
        this.ifsApplicationVersion = ifsApplicationVersion;
        this.bufferTime = bufferTime;
        this.managerComment = managerComment;
        this.deliveryMethod = deliveryMethod;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getIfsApplicationVersion() {
        return ifsApplicationVersion;
    }

    public void setIfsApplicationVersion(String ifsApplicationVersion) {
        this.ifsApplicationVersion = ifsApplicationVersion;
    }

    public int getBufferTime() {
        return bufferTime;
    }

    public void setBufferTime(int bufferTime) {
        this.bufferTime = bufferTime;
    }

    public String getManagerComment() {
        return managerComment;
    }

    public void setManagerComment(String managerComment) {
        this.managerComment = managerComment;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "training_session_virtual_machine",
            joinColumns = @JoinColumn(name = "s_id"),
            inverseJoinColumns = @JoinColumn(name = "vm_id")
    )
    @JsonIgnoreProperties("trainingSessions")
    private Set<VirtualMachine> virtualMachines;


    public Set<VirtualMachine> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(Set<VirtualMachine> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }


    //add a convience method to add virtual machines
    public void addVM(VirtualMachine virtualMachine) {

        if (virtualMachines == null) {

            virtualMachines = new HashSet<VirtualMachine>();

        }
        System.out.println("virtual machine added" + virtualMachine.getVirtualMachineId());
        virtualMachines.add(virtualMachine);


    }

    @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "training_session_trainer",
            joinColumns = @JoinColumn(name = "training_session_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )

    @JsonIgnoreProperties("trainingSessions")
    private Set<Trainer> trainers;

    public long[] getTrainerids() {
        return trainerids;
    }

    public void setTrainerids(long[] trainerids) {
        this.trainerids = trainerids;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public void add(Trainer tempTrainer) {

        if(trainers ==null) {

            trainers = new HashSet<Trainer>();

        }

        trainers.add(tempTrainer);


    }











}