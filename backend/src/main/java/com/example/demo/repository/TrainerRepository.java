package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findByType(String lastname);
    Trainer findByName (String lastname);
     Trainer findByTrainerId (long trainerId);


}
