package com.inventorymanagement.entities;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="users")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable,UserDetails{

    @Id 
    private String userid;
    private String fullname;

    @Column(unique=true ,nullable = false)
    private String email;

    private String contact;
    private String password;

    private String userstatus;

  
    //private String role;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[USER,ADMIN]
        // Collection of SimpGrantedAuthority[roles{ADMIN,USER}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}





// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
// @Column(name="user_id")
// private Integer userId;
// @Column(name="first_name")
// private String firstName;
// @Column(name="last_name")
// private  String lastName;
// @Column(name="email")
// private  String email;
// @Column(name="user_name")
//  private String userName;
//  @Column(name="password")
//  private String password;
//  @Column(name="user_status")
//  private String userStatus;S