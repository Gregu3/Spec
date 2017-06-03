package com.grzegorz.repository;

import com.grzegorz.model.Advertisement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Advertisement save(Advertisement a) {
        entityManager.persist(a);
        return (a);
    }

    public List<Advertisement> findAll() {
        TypedQuery<Advertisement> query = entityManager.createQuery("select n from Service n", Advertisement.class);
        return query.getResultList();
    }


    @Transactional
    public ResponseEntity removeOne(long id) {
        Advertisement a = entityManager.find(Advertisement.class, id);
        if (a == null) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            entityManager.remove(a);
            return new ResponseEntity(a, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @Transactional
    public Advertisement findOne(long id) {
        Advertisement a = entityManager.find(Advertisement.class, id);
        return a;
    }

    @Transactional
    public Advertisement update(long id, Advertisement a) {
        entityManager.merge(a);
        return a;
    }
}