package com.vanhack.api.core.repository;

import com.vanhack.api.core.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findBySlug(String slug);
    Optional<User> findByName(String name);
}
