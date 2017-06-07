package com.grzegorz.controller;


import com.grzegorz.config.Response;
import com.grzegorz.model.Advertisement;
import com.grzegorz.model.Category;
import com.grzegorz.repository.AdRepository;
import com.grzegorz.repository.AdService;
import com.grzegorz.repository.CategoriesRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.boot.jaxb.SourceType;
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
    AdService adService;

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
    @PostMapping("/add")
    public ResponseEntity<Advertisement> postService(@RequestBody Advertisement advertisement) {
        serviceRepository.save(advertisement);
        if ((advertisement.getId() != -1)) {
            return ResponseEntity.ok(advertisement);
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

    @GetMapping("/adv/all")
    public ResponseEntity<?> findAll() {
        List<Advertisement> all = adService.findAll();
        if (!all.isEmpty()) {
            return ResponseEntity.ok(all);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/adv/id/{id}")
    public ResponseEntity<?> findOne(@PathVariable long id) {
        Optional<Advertisement> one = adService.findOne(id);
        if (one.isPresent()) {
            return ResponseEntity.ok(one.get());
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/adv/save")
    public ResponseEntity<?> save(@RequestBody Advertisement advertisement) {
        adService.save(advertisement);
        System.out.println(advertisement.toString() +" przed if");
        if (advertisement.getId() != 0) {
            System.out.println(advertisement.toString() + " if");
            return ResponseEntity.ok(advertisement);
        } else {
            System.out.println(advertisement.toString() + " else");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}


