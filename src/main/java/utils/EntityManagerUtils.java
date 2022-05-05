package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerUtils() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public static EntityManagerFactory getInstance() {
        if (entityManagerFactory == null)
            new EntityManagerUtils();
        return entityManagerFactory;
    }
}
