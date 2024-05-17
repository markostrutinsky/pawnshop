package com.markostrutinsky.pawn_shop.repository;

import com.markostrutinsky.pawn_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteUserByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
