package com.aply.application.sptingapplication.security;

import com.aply.application.sptingapplication.model.User;
import com.aply.application.sptingapplication.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
        return SecurityUser.fromUser(user);
    }
}
