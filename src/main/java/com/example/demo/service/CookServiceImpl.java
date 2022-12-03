package com.example.demo.service;

import com.example.demo.domain.Cook;
import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.repository.CookRepository;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CookServiceImpl implements CookService{


    @Autowired
    CookRepository cookRepository;
    @Autowired
    RecipeRepository recipeRepository;

    //dodawanie i usuwanie kucharza
    public List<Cook> getAllCooks() throws RecipeException {
        List<Cook> cooks = cookRepository.findAll();
        if(cooks.isEmpty()) throw  new RecipeException("Service.COOK_NOT_FOUND");
        return cooks;
    }
    public Integer addCook(Cook cook) throws RecipeException{
        Cook cook1 = new Cook();
        cook1.setId(cook.getId());
        cook1.setAuthor(cook.getAuthor());
        cookRepository.save(cook1);
        return cook1.getId();
    }
    public void deleteCook(Integer cookId) throws RecipeException{
        Optional<Cook> optional = cookRepository.findById(cookId);
        Cook cook = optional.orElseThrow(()->new RecipeException("Service.COOK_NOT_FOUND"));
        cookRepository.delete(cook);
    }

    //dodawanie, aktualizacja, usuwanie receptury, pokaz wszystkie nazwy przepisow
    public List<String> getAllRecipesByName() throws RecipeException {
        List<String> recipes = recipeRepository.findAllByName();
        if(recipes.isEmpty()) throw new RecipeException("Service.RECIPES_NOT_FOUND");
        return recipes;
    }

    public void addRecipeIfCookExisting(Integer cookId, Recipe recipe) throws RecipeException {
        Optional<Cook> optional = cookRepository.findById(cookId);
        Cook cook = optional.orElseThrow(()->new RecipeException("Service.COOK_NOT_FOUND"));
        recipeRepository.save(recipe);
        recipe.setCook(cook);
        cookRepository.save(cook);
       // recipeRepository.save(recipe);
    }

    public void deleteRecipeOfExistingCook(Integer cookId, Integer recipeId) throws RecipeException{
        Optional<Cook> optional = cookRepository.findById(cookId);
        Cook cook = optional.orElseThrow(()->new RecipeException("Service.COOK_NOT_FOUND"));

        Optional<Recipe> optional2 = recipeRepository.findById(recipeId);
        Recipe recipe = optional2.orElseThrow(()->new RecipeException("Service.RECIPES_NOT_FOUND"));
        recipeRepository.delete(recipe);
    }



    public void updateRecipe(Integer recipeId, String instruction) throws RecipeException{
        Optional<Recipe> optional2 = recipeRepository.findById(recipeId);
        Recipe recipe = optional2.orElseThrow(()->new RecipeException("Service.RECIPES_NOT_FOUND"));
        recipe.setInstruction(instruction);

    }






}
