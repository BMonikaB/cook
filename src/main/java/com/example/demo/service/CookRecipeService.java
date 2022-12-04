package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;

public interface CookRecipeService {

    void addRecipeIfCookExisting(Integer cookId, Recipe recipe) throws RecipeException;
    void deleteRecipeOfExistingCook(Integer cookId, Integer recipeId) throws RecipeException;

}
