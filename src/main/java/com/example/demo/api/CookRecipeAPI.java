package com.example.demo.api;

import com.example.demo.domain.Cook;
import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.service.CookRecipeServiceImpl;
import com.example.demo.service.CookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping()
public class CookRecipeAPI {


    @Autowired
    CookRecipeServiceImpl cookRecipeService;
    @Autowired
    private Environment environment;





    //tworzenie przepisu dla danego kucharza
    @PostMapping(value = "/recipee/{cookId}")
    ResponseEntity<String> addRecipeIfCookExisting( @PathVariable Integer cookId, @Valid @RequestBody Recipe recipe) throws RecipeException{
        try {
            cookRecipeService.addRecipeIfCookExisting(cookId, recipe);
            String successMessage = environment.getProperty("API.INSERT_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

    //usuwanie przepisu danego kucharza
    @DeleteMapping(value = "/cooks/{cookId}/{recipeId}")
    public ResponseEntity<String> deleteRecipeOfExistingCook(@PathVariable Integer cookId,@PathVariable Integer recipeId) throws RecipeException{
       try {
           cookRecipeService.deleteRecipeOfExistingCook(cookId, recipeId);
           String successMessage = environment.getProperty("API.INSERT_SUCCESS");
           return new ResponseEntity<>(successMessage, HttpStatus.OK);
       }catch (Exception exception) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                   environment.getProperty(exception.getMessage()), exception);
       }
    }











}
