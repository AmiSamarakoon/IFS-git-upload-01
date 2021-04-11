package com.example.demo.dao;

import com.example.demo.model.VirtualMachine;
import com.example.demo.payload.SortRequestVM;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirtualMachineDao {

    List<VirtualMachine> findData(SortRequestVM request);

}
