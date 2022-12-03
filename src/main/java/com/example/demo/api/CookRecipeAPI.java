package com.example.demo.api;

import com.example.demo.domain.Cook;
import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.service.CookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class CookRecipeAPI {


    @Autowired
    CookServiceImpl cookService;
    @Autowired
    private Environment environment;


    @GetMapping("/cooks")
    public ResponseEntity<List<Cook>> getAllCooks() throws RecipeException {
        try {
            List<Cook> cookList = cookService.getAllCooks();
            return new ResponseEntity<>(cookList, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }

    }

    @PostMapping(value = "/cooks")
    ResponseEntity<String> addCook(@RequestBody Cook cook) throws RecipeException{
        try {
            Integer cook1 = cookService.addCook(cook);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS") + cook1;
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    @DeleteMapping(value = "/cook/{cookId}")
    public ResponseEntity<String> deleteCook(@PathVariable Integer cookId) throws RecipeException{
        try {
            cookService.deleteCook(cookId);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }



    ///


    @GetMapping("/recipes")
    public ResponseEntity<List<String>> getAllRecipes() throws RecipeException {
        try {
            List<String> recipes = cookService.getAllRecipesByName();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    @PostMapping(value = "/recipes/{cookId}")
    ResponseEntity<String> addRecipeIfCookExisting(@PathVariable Integer cookId, @RequestBody Recipe recipe) throws RecipeException{
        try {
            cookService.addRecipeIfCookExisting(cookId, recipe);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    @DeleteMapping(value = "/cook/{cookId}/{recipeId}")
    public ResponseEntity<String> deleteRecipeOfExistingCook(@PathVariable Integer cookId,@PathVariable Integer recipeId) throws RecipeException{
       try {
           cookService.deleteRecipeOfExistingCook(cookId, recipeId);
           String successMessage = environment.getProperty("API.INSERT_SUCCESS");
           return new ResponseEntity<>(successMessage, HttpStatus.OK);
       }catch (Exception exception) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                   environment.getProperty(exception.getMessage()), exception);
       }
    }

    @PutMapping(value = "/recipe/{recipeId}")
    public ResponseEntity<String> updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe recipe) throws RecipeException{
        try {
            cookService.updateRecipe(recipeId, recipe.getInstruction());
            String successMessage = environment.getProperty("API.INSERT_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }






}
