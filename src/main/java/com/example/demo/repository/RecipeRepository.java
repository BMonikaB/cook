package com.example.demo.repository;

import com.example.demo.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("SELECT r.name  FROM Recipe r")
    List<String> findAllByName();

}
