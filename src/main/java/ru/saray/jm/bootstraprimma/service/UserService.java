package ru.saray.jm.bootstraprimma.service;

import ru.saray.jm.bootstraprimma.model.User;

import java.util.List;

public interface UserService {
    User loadUserByUsername(String username);
    User loadUserByEmail(String email);
    List<User> getUsers();
    User getUserByID(Long id);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
}