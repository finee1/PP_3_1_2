package org.kata.springbootapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.kata.springbootapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getUserById(id));
    }
}
