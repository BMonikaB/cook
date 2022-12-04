package com.example.demo.service;

import com.example.demo.domain.Cook;
import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.repository.CookRepository;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class CookRecipeServiceImpl implements CookRecipeService{

    @Autowired
    CookRepository cookRepository;
    @Autowired
    RecipeRepository recipeRepository;


    //dodaj recepte do konkrtnego kucharza/uzytkownika - jezeli taki istnieje
    public void addRecipeIfCookExisting(Integer cookId, Recipe recipe) throws RecipeException {
        Optional<Cook> optional = cookRepository.findById(cookId);
        Cook cook = optional.orElseThrow(() -> new RecipeException("Service.COOK_NOT_FOUND"));
        recipeRepository.save(recipe);
        recipe.setCook(cook);
        cookRepository.save(cook);
    }

    //usun konkrtney przepis od konkretnego kucharza/uzytkownika, jezeli istnieje
    public void deleteRecipeOfExistingCook(Integer cookId, Integer recipeId) throws RecipeException {
        Optional<Cook> optional = cookRepository.findById(cookId);
        Cook cook = optional.orElseThrow(() -> new RecipeException("Service.COOK_NOT_FOUND"));

        Optional<Recipe> optional2 = recipeRepository.findById(recipeId);
        Recipe recipe = optional2.orElseThrow(() -> new RecipeException("Service.RECIPE_NOT_FOUND"));
        recipeRepository.delete(recipe);
    }
}


