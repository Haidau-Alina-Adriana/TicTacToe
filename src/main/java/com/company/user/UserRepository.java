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

    public static int getNumberOfUsersFromDatabase() {
        return entityManager.createNamedQuery("Users.getNumberOfUsers")
                .getResultList()
                .size();
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

    public void deleteUser(TictactoeUsersEntity user) {
        beginTransaction();
        TictactoeUsersEntity findUser = entityManager.find(TictactoeUsersEntity.class, user.getId());
        entityManager.remove(findUser);
        commitTransaction();
    }

    public void updateEmail(TictactoeUsersEntity user, String email) {
        beginTransaction();
        TictactoeUsersEntity findUser = entityManager.find(TictactoeUsersEntity.class, user.getId());
        findUser.setEmail(email);
        commitTransaction();
    }

    public void updateUsername(TictactoeUsersEntity user, String username) {
        beginTransaction();
        TictactoeUsersEntity findUser = entityManager.find(TictactoeUsersEntity.class, user.getId());
        findUser.setUsername(username);
        commitTransaction();
    }

    public void updateScore(long userID){
        beginTransaction();
        TictactoeUsersEntity findUser = entityManager.find(TictactoeUsersEntity.class, userID);
        findUser.setScore(findUser.getScore() + 3);
        commitTransaction();
    }

    public static List<TictactoeUsersEntity> getStatistics() {
        return entityManager.createNamedQuery("Users.sortUsersByScore")
                .getResultList();
    }

}
