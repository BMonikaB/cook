package com.example.demo.repository;

import com.example.demo.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("SELECT r.name  FROM Recipe r")
    List<String> findAllByName();

    Optional<Recipe> findByName(String recipeName);

    void deleteByName(String recipeeName);
}
