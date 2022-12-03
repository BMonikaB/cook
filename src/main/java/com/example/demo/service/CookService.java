package com.example.demo.service;

import com.example.demo.domain.Cook;
import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;

import java.util.List;

public interface CookService {


    List<Cook> getAllCooks() throws RecipeException;
    Integer addCook(Cook cook) throws RecipeException;
    void deleteCook(Integer cookId) throws RecipeException;
    List<String> getAllRecipesByName() throws RecipeException;
    void addRecipeIfCookExisting(Integer cookId, Recipe recipe) throws RecipeException;
    void deleteRecipeOfExistingCook(Integer cookId, Integer recipeId) throws RecipeException;
    void updateRecipe(Integer recipeId, String instruction) throws RecipeException;
}
