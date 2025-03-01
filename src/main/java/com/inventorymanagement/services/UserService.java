package com.inventorymanagement.services;

import java.util.*;

import com.inventorymanagement.entities.User;

public interface UserService {

    User saveUser(User user);

    void deleteUser(String userid);

    List<User> getUsers();

    Optional<User> updateUser(User user);

    Boolean isUserExist(String email,String password);


    User getUserByEmail(String email);
}
