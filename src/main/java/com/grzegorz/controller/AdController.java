package com.grzegorz.controller;


import com.grzegorz.model.Advertisement;
import com.grzegorz.model.Category;
import com.grzegorz.repository.AdRepository;
import com.grzegorz.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ad")
public class AdController {

    @Autowired
    AdRepository serviceRepository;

    @Autowired
    CategoriesRepository categoriesRepository;


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Advertisement> ServicesList = serviceRepository.findAll();
        if (ServicesList.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(ServicesList);
    }

    @Transactional
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categoriesList = categoriesRepository.findAll();
        if (categoriesList.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(categoriesList);
    }

    @Transactional
    @PostMapping("add")
    public ResponseEntity<Advertisement> postService(@RequestBody Advertisement Service) {
        serviceRepository.save(Service);
        if ((Service.getId() != -1)) {
            return ResponseEntity.ok(Service);
        }
        return new ResponseEntity<Advertisement>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Advertisement> getOneService(@PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Advertisement Service = serviceRepository.findOne(id.get());
            if (Service != null) {
                return new ResponseEntity<Advertisement>(Service, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Advertisement>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Advertisement>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Advertisement> deleteService(@PathVariable long id) {
        System.out.println("ServiceController.deleteService");
        if (id != -1) {
            System.out.println("ServiceController.deleteService");
            Advertisement a = serviceRepository.findOne(id);
            serviceRepository.removeOne(id);
            if (a != null) {
                System.out.println("ServiceController.deleteService ok");
                return new ResponseEntity(a, new HttpHeaders(), HttpStatus.OK);
            } else {
                System.out.println("ServiceController.deleteService 404");
                return new ResponseEntity(new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } else {
            System.out.println("ServiceController.deleteService 400");
            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/update/")
    public ResponseEntity<Advertisement> update(@RequestBody Advertisement Service) {
        serviceRepository.update(Service.getId(), Service);
        return new ResponseEntity<Advertisement>(Service, new HttpHeaders(), HttpStatus.OK);
    }
}


