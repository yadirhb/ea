package edu.mum.dao.impl;


import edu.mum.dao.UserDao;
import edu.mum.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super();

        this.setDaoType(User.class);
    }

    public User findByEmail(String email) {
        Query query = entityManager.createQuery("select u from User u  where u.email =:email");

        return (User) query.setParameter("email", email).getSingleResult();
    }
}