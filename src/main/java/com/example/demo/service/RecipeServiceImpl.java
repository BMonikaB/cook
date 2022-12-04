package com.example.demo.service;


import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.repository.CookRepository;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    CookRepository cookRepository;
    @Autowired
    RecipeRepository recipeRepository;


    //pobierz wszystkie przpisy po nazwie
    public List<String> getAllRecipesByName() throws RecipeException {
        List<String> recipes = recipeRepository.findAllByName();
        if(recipes.isEmpty()) throw new RecipeException("Service.RECIPES_NOT_FOUND");
        return recipes;
    }

    //pobierz jeden przepis po nazwie
    public Recipe getRecipeByName(String recipeName) throws RecipeException{
        Optional<Recipe> optional = recipeRepository.findByName(recipeName);
        Recipe recipe = optional.orElseThrow(()-> new RecipeException("Service.RECIPES_NOT_FOUND"));
        return recipe;
    }


    //uaktualnij przepis jeżeli jego nazwa istnieje
    public void updateRecipeByName(String recipeName, String instruction) throws RecipeException{
        Optional<Recipe> optional2 = recipeRepository.findByName(recipeName);
        Recipe recipe = optional2.orElseThrow(()->new RecipeException("Service.RECIPES_NOT_FOUND"));
        recipe.setInstruction(instruction);
    }

    //dodaj przepis
    public Integer addRecipe(Recipe recipe){
        recipeRepository.save(recipe);
        return recipe.getId();
    }


    //usun przepis po nazwie jezeli istnieje
    public void deleteRecipeByName(String recipeeName) throws RecipeException{
        Optional<Recipe> optional = recipeRepository.findByName(recipeeName);
        Recipe recipe = optional.orElseThrow(()->new RecipeException("Service.RECIPES_NOT_FOUND"));
        recipeRepository.deleteByName(recipeeName);

    }


}
