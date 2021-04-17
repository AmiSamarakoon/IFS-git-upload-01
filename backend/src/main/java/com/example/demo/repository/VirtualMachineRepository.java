package com.example.demo.repository;

import com.example.demo.model.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VirtualMachineRepository extends JpaRepository<VirtualMachine,Long> {

    @Query("select v from TrainingSession t join t.virtualMachines v where t.id=(:id) ")
    List<VirtualMachine> findVirtualMachineByTrainingSessions(@Param("id") Long id);

    @Query("select v from VirtualMachine v where v.product=(:product)")
    List<VirtualMachine> findAllByProduct(@Param("product") String product);

    VirtualMachine findVirtualMachineByVirtualMachineId (long id);




}
