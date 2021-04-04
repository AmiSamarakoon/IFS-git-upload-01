package com.example.demo.repository;


import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String str);

}
