package com.grzegorz.controller;


import com.grzegorz.model.Role;
import com.grzegorz.model.Advertisement;
import com.grzegorz.model.User;
import com.grzegorz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add/{role}")
    public ResponseEntity<User> postUser(@RequestBody User user, @PathVariable boolean role) {
        if (role == true) {
            user.setRole(Role.ROLE_COMPANY);
        } else {
            user.setRole(Role.ROLE_USER);
        }
        System.out.println(user.getRole());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteEmployee(@PathVariable Optional<Long> id) {
        if (!id.equals(null)) {
            User u = userRepository.findOne(id.get());
            userRepository.removeOne(id.get());
            if (u != null) {
                return new ResponseEntity(u, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity(new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getDetailsOfUsers(@PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            User user = userRepository.findOne(id.get());
            System.out.println(user.getEmail() + " " + user.getFirstName());
            if (user != null) {
                return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/putRelation/{id}")
    public ResponseEntity<?> updateRel(@PathVariable long id, @RequestBody Advertisement service) {
        userRepository.updateRel(id, service);
        return new ResponseEntity<>(service, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/deleteCar/id/{id}/{idCar}")
    public ResponseEntity<?> deleteCar(@PathVariable long id, @PathVariable long idCar) {
        userRepository.deleteRel(id, idCar);
        return new ResponseEntity<>(idCar, new HttpHeaders(), HttpStatus.OK);
    }
}

