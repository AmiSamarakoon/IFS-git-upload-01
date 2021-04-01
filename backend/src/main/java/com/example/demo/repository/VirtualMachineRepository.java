package com.example.demo.repository;


import com.example.demo.model.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualMachineRepository extends JpaRepository<VirtualMachine,Long> {



}
