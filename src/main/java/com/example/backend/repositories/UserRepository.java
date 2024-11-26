package com.example.backend.repositories;

import com.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.candidate c " +
            "LEFT JOIN u.company comp " +
            "WHERE c.email = :email OR comp.email = :email")
    User findByCandidateEmailOrCompanyEmail(String email);
}
