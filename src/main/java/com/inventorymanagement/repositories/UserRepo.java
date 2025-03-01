package com.inventorymanagement.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
 
    //User findByEmail(String email);
    Optional<User> findByEmail(String email);

    Boolean findByEmailAndPassword(String email, String password);

   // Optional<User> findByEmailToken(String id);
}
