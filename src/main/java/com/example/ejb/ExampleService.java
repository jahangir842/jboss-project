package com.example.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ExampleService {

    @PersistenceContext
    private EntityManager entityManager;

    public String getMessage() {
        return "Hello from EJB!";
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }
} 