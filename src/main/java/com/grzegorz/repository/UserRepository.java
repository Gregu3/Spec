package com.grzegorz.repository;

import com.grzegorz.model.Advertisement;
import com.grzegorz.model.User;
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
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void save(User u) {
        entityManager.persist(u);
    }


    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Transactional
    public ResponseEntity removeOne(long id) {
        User u = entityManager.find(User.class, id);
        if (u == null) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            entityManager.remove(u);
            return new ResponseEntity(u, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @Transactional
    public User findOne(long id) {
        User u = entityManager.find(User.class, id);
        return u;
    }


    @Transactional
    public User updateRel(long id, Advertisement Service) {
        User user = entityManager.find(User.class, id);
        user.getAdvertisements().add(Service);
        entityManager.merge(user);
        return user;
    }

    @Transactional
    public User getUser(String email) {
        TypedQuery<User> users = entityManager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email);
        if (users.getResultList().isEmpty()) {
            return null;
        } else {
            List<User> userList = users.getResultList();
            return userList.get(0);
        }
    }

    @Transactional
    public User deleteRel(long id, long idService) {
        User user = entityManager.find(User.class, id);
        System.out.println(user.getAdvertisements().size());
        for (int i = 0; i < user.getAdvertisements().size(); i++) {
            user.getAdvertisements().indexOf(i);
            if (user.getAdvertisements().get(i).getId() == idService) {
                user.getAdvertisements().remove(i);
            }
        }
        entityManager.merge(user);
        return user;
    }
}

