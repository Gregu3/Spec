//package com.grzegorz.controller;
//
//import com.grzegorz.model.Advertisement;
//import com.grzegorz.repository.AdvertisementRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//
//@RestController
//@RequestMapping("ad")
//public class AdvertisementController {
//    @Autowired
//    AdvertisementRepository advertisementRepository;
//
//
//    @GetMapping("/all")
//    public ResponseEntity<?> getAll() {
//        List<Advertisement> autosList = advertisementRepository.findAll();
//        if (autosList.isEmpty())
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        return ResponseEntity.ok(autosList);
//    }
//
//    @Transactional
//    @PostMapping("add")
//    public ResponseEntity<Advertisement> postAuto(@RequestBody Advertisement auto) {
//        advertisementRepository.save(auto);
//        if ((auto.getId() != -1)) {
//            return ResponseEntity.ok(auto);
//        }
//        return new ResponseEntity<Advertisement>(HttpStatus.BAD_REQUEST);
//    }
////
////    @GetMapping("/id/{id}")
////    public ResponseEntity<Advertisement> getOneAuto(@PathVariable Optional<Long> id) {
////        if (id.isPresent()) {
////            Advertisement auto = advertisementRepository.findOne(id.get());
////            if (auto != null) {
////                return new ResponseEntity<Advertisement>(auto, new HttpHeaders(), HttpStatus.OK);
////            } else {
////                return new ResponseEntity<Advertisement>(HttpStatus.NOT_FOUND);
////            }
////        }
////        return new ResponseEntity<Advertisement>(HttpStatus.BAD_REQUEST);
////    }
////
////
////    @DeleteMapping("delete/id/{id}")
////    public ResponseEntity<Advertisement> deleteAuto(@PathVariable long id) {
////        //   System.out.println("AutoController.deleteAuto");
////        if (id != -1) {
////            //   System.out.println("AutoController.deleteAuto");
////            Advertisement a = advertisementRepository.findOne(id);
//////            advertisementRepository.removeOne(id);
////            if (a != null) {
////                System.out.println("AdvertisementController.deleteAuto ok");
////                return new ResponseEntity(a, new HttpHeaders(), HttpStatus.OK);
////            } else {
////                System.out.println("AdvertisementController.deleteAuto 404");
////                return new ResponseEntity(new HttpHeaders(), HttpStatus.NOT_FOUND);
////            }
////        } else {
////            @PostMapping("/update/")
////            public ResponseEntity<Advertisement> update(@RequestBody Advertisement auto) {
//////        advertisementRepository.update(auto.getId(), auto);
////                return new ResponseEntity<Advertisement>(auto, new HttpHeaders(), HttpStatus.OK);
////            }
////
////            System.out.println("AdvertisementController.deleteAuto 400");
////            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
////        }
////    }
////
////    @Transactional
//
//}
