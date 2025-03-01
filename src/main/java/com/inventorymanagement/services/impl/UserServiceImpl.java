package com.inventorymanagement.services.impl;

import java.util.List;
import java.util.Optional;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventorymanagement.entities.User;
import com.inventorymanagement.helpers.AppConstants;
import com.inventorymanagement.helpers.ResouceNotFoundException;
import com.inventorymanagement.repositories.UserRepo;
import com.inventorymanagement.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserid(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepo.save(user);
    }

    @Override
    public void deleteUser(String userid) {

        userRepo.deleteById(userid);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> updateUser(User user) {

        User user2 = userRepo.findById(user.getUserid())
                .orElseThrow(() -> new ResouceNotFoundException("User Not Found"));

        user2.setFullname(user.getFullname());
        user2.setEmail(user.getEmail());
        user2.setContact(user.getContact());

        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public Boolean isUserExist(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'isUserExist'");
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);

    }

}
