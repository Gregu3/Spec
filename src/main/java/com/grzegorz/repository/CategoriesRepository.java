package com.grzegorz.repository;

import com.grzegorz.model.Category;
import com.grzegorz.model.User;
import org.springframework.stereotype.Repository;
import sun.awt.geom.AreaOp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by grzeg on 03.06.2017.
 */
@Repository
public class CategoriesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("select  c from Category c", Category.class);
        return query.getResultList();
    }

    @Transactional
    public Category getByName(String name) {
        TypedQuery<Category> query = entityManager.createQuery("select  c from Category c where c.name= :name",
                Category.class).setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }

    @Transactional
    public Category saveCategory(Category c) {
        entityManager.persist(c);
        return c;
    }
}
