package com.markostrutinsky.pawn_shop.repository;

import com.markostrutinsky.pawn_shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleUser);

    boolean existsByName(String name);
}
