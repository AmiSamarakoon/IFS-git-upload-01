package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "virtual_machine", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "vm_name"
        })
})
public class VirtualMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long virtualMachineId;

    @Column(name="vm_name")
    private String virtualMachineName;

    @Column(name="product")
    private String product;

    @Column(name="version")
    private String version;

    @Column(name="region")
    private String region;





    public VirtualMachine() {
    }

    public VirtualMachine(String virtualMachineName, String product, String version, String region) {
        super();
        this.virtualMachineName = virtualMachineName;
        this.product = product;
        this.version = version;
        this.region = region;

    }

    public long getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(long virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getVirtualMachineName() {
        return virtualMachineName;
    }

    public void setVirtualMachineName(String virtualMachineName) {
        this.virtualMachineName = virtualMachineName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }







    @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "training_session_virtual_machine",
            joinColumns = @JoinColumn(name = "vm_id"),
            inverseJoinColumns = @JoinColumn(name = "s_id")
    )
    //@JsonIgnoreProperties("virtualMachines")
    private List<TrainingSession> trainingSessions;




    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }


    //adding a convenience method


    public void add(TrainingSession tempSession) {

        if(trainingSessions ==null) {

            trainingSessions = new ArrayList<TrainingSession>();

        }

        trainingSessions.add(tempSession);


    }






}
