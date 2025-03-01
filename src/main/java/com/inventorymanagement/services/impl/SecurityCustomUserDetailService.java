package com.inventorymanagement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.inventorymanagement.repositories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    //     User user = userRepo.findByEmail(username);
    //     if (user == null) {
    //         throw new UsernameNotFoundException("User not found");
    //     }
    //    SimpleGrantedAuthority roles=new SimpleGrantedAuthority(user.getRole());
       
    
    //     return new org.springframework.security.core.userdetails.User(
    //             user.getUsername(),
    //             user.getPassword(),
    //             user.isEnabled(), // account status (active or inactive)
    //             true,             // account is not expired
    //             true,             // credentials are not expired
    //             true,             // account is not locked
    //             List.of(roles)      // user roles (authorities)
    //     );
        
       return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with Email"+username));
    }



}
