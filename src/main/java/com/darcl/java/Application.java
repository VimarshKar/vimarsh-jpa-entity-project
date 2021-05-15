package com.darcl.java;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("example-unit");

    public static void main(String[] args) {
        try {
            persistEntity();
            loadEntity();
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void persistEntity() {
        Darcl employee = new Darcl();
        employee.setName("Megan");
        employee.setDept("Admin");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    private static void loadEntity() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.storeMode", CacheRetrieveMode.BYPASS);
        Darcl employee = em.find(Darcl.class, 1L, properties);
        System.out.println("Employee loaded: " + employee);
        em.close();
    }
}