package com.inventorymanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entities.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Integer> {

}
