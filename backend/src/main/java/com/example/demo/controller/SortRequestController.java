package com.example.demo.controller;

import com.example.demo.dao.TrainingSessionDao;
import com.example.demo.dao.VirtualMachineDao;
import com.example.demo.model.TrainingSession;
import com.example.demo.model.VirtualMachine;
import com.example.demo.payload.SortRequestTS;
import com.example.demo.payload.SortRequestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/sort")
public class SortRequestController {

    @Autowired
    TrainingSessionDao trainingSessionDao;

    @Autowired
    VirtualMachineDao virtualMachineDao;

    @PostMapping("/trainingSessions")
    public List<TrainingSession> sortTrainingSessions(@RequestBody SortRequestTS request) {
        List<TrainingSession> data = trainingSessionDao.findData(request);
        return data;
    }

    @PostMapping("/virtualMachines")
    public List<VirtualMachine> sortVirtualMachines(@RequestBody SortRequestVM request) {
        List<VirtualMachine> data = virtualMachineDao.findData(request);
        return data;
    }
}
