package com.example.ecommerce.repositories;

import com.example.ecommerce.domain.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
}
