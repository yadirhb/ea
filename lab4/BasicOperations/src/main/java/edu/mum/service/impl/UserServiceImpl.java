package edu.mum.service.impl;

import edu.mum.dao.UserDao;
import edu.mum.domain.User;
import edu.mum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public void save(User user) {
        userDao.save(user);
    }


    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


}
