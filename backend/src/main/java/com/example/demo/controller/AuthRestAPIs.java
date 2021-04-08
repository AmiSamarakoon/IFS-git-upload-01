package com.example.demo.controller;

import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.AddTrainerForm;
import com.example.demo.message.response.JwtResponse;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.model.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    RoleRepository roleRepository;



    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/add-trainer")
    @PreAuthorize("hasRole('MANAGER')")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody AddTrainerForm addTrainerForm) {
        if (trainerRepository.existsByUsername(addTrainerForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (trainerRepository.existsByEmail(addTrainerForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        User user = new User( addTrainerForm.getUsername(),
                encoder.encode(addTrainerForm.getPassword()));

        Trainer trainer = new Trainer(addTrainerForm.getName(),addTrainerForm.getUsername(), addTrainerForm.getType(), addTrainerForm.getQualifications(), addTrainerForm.getEmail(), addTrainerForm.getContactNo());


        Set<String> strRoles = addTrainerForm.getRole();
        Set<Role> roles = new HashSet<>();




        strRoles.forEach(role -> {
            switch (role) {
                case "manager":
                    Role managerRole = roleRepository.findByName(RoleName.ROLE_MANAGER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(managerRole);

                    break;
                case "depmanager":
                    Role depManagerRole = roleRepository.findByName(RoleName.ROLE_DEPMANAGER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(depManagerRole);

                    break;
                default:
                    Role trainerRole = roleRepository.findByName(RoleName.ROLE_TRAINER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    System.out.println(trainerRole.toString());
                    roles.add(trainerRole);
            }
        });


        user.setRoles(roles);
        userRepository.save(user);
        trainerRepository.save(trainer);


        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

}
