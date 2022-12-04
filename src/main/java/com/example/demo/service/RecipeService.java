package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;

import java.util.List;

public interface RecipeService {


    List<String> getAllRecipesByName() throws RecipeException;
    Recipe getRecipeByName(String recipeName) throws RecipeException;
    public void updateRecipeByName(String recipeName, String instruction) throws RecipeException;
    public Integer addRecipe(Recipe recipe);
    public void deleteRecipeByName(String recipeeName) throws RecipeException;

}
