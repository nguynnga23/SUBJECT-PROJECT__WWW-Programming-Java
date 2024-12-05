package com.example.backend.repositories;

import com.example.backend.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository  extends JpaRepository<Experience, Long> {

}
