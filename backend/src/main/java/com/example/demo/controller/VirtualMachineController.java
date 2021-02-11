package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VirtualMachine;
import com.example.demo.repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class VirtualMachineController {

    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    //get all virtual machines
    @GetMapping("/virtualMachines")
    public List<VirtualMachine> getAllVirtualMachines() {
        return virtualMachineRepository.findAll();
    }

    //add virtual machines
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/virtualMachines")
    public VirtualMachine addVirtualMachine(@RequestBody VirtualMachine virtualMachine) {
        return virtualMachineRepository.save(virtualMachine);
    }

    //get virtual machine by id
    @GetMapping("/virtualMachines/{id}")
    public ResponseEntity<VirtualMachine> getVirtualMachineById(@PathVariable Long id) {
        VirtualMachine virtualMachine = virtualMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Virtual Machine Not Found"));
        return ResponseEntity.ok(virtualMachine);
    }

    //delete virtual machine
    @DeleteMapping("/virtualMachines/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteVirtualMachine(@PathVariable Long id){
        VirtualMachine virtualMachine = virtualMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Virtual Machine Not Found"));

        virtualMachineRepository.delete(virtualMachine);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
