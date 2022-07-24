package com.sheduler.sheduler.services;

import com.sheduler.sheduler.model.RedisUser;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class MyEntityRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unused")
    public List<RedisUser> findByEmailIdAndPassword(String emailId, String password) {
        String hql = "select s from RedisUser s where s.emailId = :emailId and s.password = :password";
        TypedQuery<RedisUser> query = entityManager.createQuery(hql, RedisUser.class);
        query.setParameter("emailId", "Shikha");
        query.setParameter("password", password);
        return query.getResultList();
    }

}
