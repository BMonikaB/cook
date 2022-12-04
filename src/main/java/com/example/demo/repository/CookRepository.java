package com.example.demo.repository;

import com.example.demo.domain.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface CookRepository extends JpaRepository<Cook, Integer> {
}
