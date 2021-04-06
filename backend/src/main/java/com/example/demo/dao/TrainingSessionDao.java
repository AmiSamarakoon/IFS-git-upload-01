package com.example.demo.dao;

import com.example.demo.model.TrainingSession;
import com.example.demo.payload.SortRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingSessionDao {
    List<TrainingSession> findData(SortRequest request);
}
