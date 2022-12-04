package com.example.demo.api;

import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.service.CookServiceImpl;
import com.example.demo.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipees")
public class RecipeAPI {

    @Autowired
    RecipeServiceImpl recipeService;
    @Autowired
    private Environment environment;



    //pokaz wszytskie przepisy - tylko po nazwie
    @GetMapping()
    public ResponseEntity<List<String>> getAllRecipes() throws RecipeException {
        try {
            List<String> recipes = recipeService.getAllRecipesByName();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }




    //uaktualnij dany przepis po nazwie
    @PutMapping(value = "/{recipeName}")
    public ResponseEntity<String> updateRecipe(@PathVariable String recipeName, @Valid @RequestBody Recipe recipe) throws RecipeException{
        try {
            recipeService.updateRecipeByName(recipeName, recipe.getInstruction());
            String successMessage = environment.getProperty("API.INSERT_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }



    //stworz przepis
    @PostMapping()
    ResponseEntity<String> addRecipeIfCookExisting(@Valid @RequestBody Recipe recipe) throws RecipeException {
        try {
            Integer recipe1 = recipeService.addRecipe(recipe);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS") + recipe1;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    //wykszukaj dany przepis po nazwie
    @GetMapping("/{name}")
    ResponseEntity<Recipe> getRecipeByName(@PathVariable String name) throws RecipeException{
        try{
            Recipe recipe = recipeService.getRecipeByName(name);
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }

    }




    //usun dany przepis po nazwie
    @DeleteMapping("/{recipeName}")
    public ResponseEntity<String> deleteRecipeById(@PathVariable String recipeName) throws RecipeException{
        try {
            recipeService.deleteRecipeByName(recipeName);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }



}
