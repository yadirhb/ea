package edu.mum.service;

import edu.mum.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    User findByEmail(String email);
}
