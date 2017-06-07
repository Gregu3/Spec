//package com.grzegorz.repository;
//
//import com.grzegorz.model.Advertisement;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
//import java.util.List;
//
//
//public class AdvertisementRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Transactional
//    public Advertisement save(Advertisement a) {
//
//        System.out.println(a.getImage() + " wnetrze fota auto");
//        if (a.getImage() == null) {
//            long id = 1;  // UWAGA NA ID OBIEKTU
//            Advertisement auto = entityManager.find(Advertisement.class, id);
//            System.out.println("udało sie");
//            a.setImage(auto.getImage());
//        }
//        entityManager.persist(a);
//
//        return (a);
//
//    }
//
//    public List<Advertisement> findAll() {
//        TypedQuery<Advertisement> query = entityManager.createQuery("select n from Advertisement n", Advertisement.class);
//        return query.getResultList();
//    }
//
//
//
//
//    @Transactional
//    public Advertisement findOne(long id) {
//        Advertisement a = entityManager.find(Advertisement.class, id);
//        return a;
//    }
//
//
//
//}