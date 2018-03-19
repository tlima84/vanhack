package com.vanhack.api.core.service.userdetail;

import com.vanhack.api.core.repository.model.User;
import com.vanhack.api.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name){

        User user = userService.getUser(name);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), emptyList());
    }
}
