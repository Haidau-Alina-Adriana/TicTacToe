package com.company.user;

import jpa.entity.TictactoeUsersEntity;
import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserRepository {
    public static EntityManager entityManager;
    public static EntityTransaction entityTransaction;

    public UserRepository() {
        entityManager = EntityManagerUtils.getInstance().createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void create(TictactoeUsersEntity entity) {
        beginTransaction();
        entityManager.persist(entity);
        commitTransaction();
//        System.out.println("Created successfully.");
    }

    protected void beginTransaction() {
        try {
            entityTransaction.begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() {
        try {
            entityTransaction.commit();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public List<TictactoeUsersEntity> findByEmail(String email) {
        return entityManager.createNamedQuery("Users.findByEmail")
                .setParameter("inputEmail", email)
                .getResultList();
    }

    public int findByUsername(String username) {
        return entityManager.createNamedQuery("Users.findByUsername")
                .setParameter("inputUsername", username)
                .getResultList()
                .size();
    }

//    public static List<TictactoeUsersEntity> findByIdAndEmail(String name) {
//        return entityManager.createNamedQuery("Continent.findByName")
//                .setParameter("continentName", name)
//                .getResultList();
//    }

//    public static TictactoeUsersEntity loginUser(User user) {
//
//    }
}
