package com.example.demo.service;

import com.example.demo.repository.UserAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService implements UserDetailsService {

    @Autowired
    private UserAdminRepo userAdminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAdminRepo.findByEmail(username).orElseThrow();
    }
}
