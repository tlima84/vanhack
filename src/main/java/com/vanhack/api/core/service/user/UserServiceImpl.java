package com.vanhack.api.core.service.user;

import com.vanhack.api.core.exception.EntityNotFoundException;
import com.vanhack.api.core.repository.UserRepository;
import com.vanhack.api.core.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String slug) {

        return userRepository.findByName(slug).orElseThrow(() -> new EntityNotFoundException(String.format("User not found for slug='%s'", slug)));
    }
}
