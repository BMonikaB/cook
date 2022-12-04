package com.example.demo.api;

import com.example.demo.domain.Cook;
import com.example.demo.exception.RecipeException;
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
@RequestMapping("/cooks")
public class CookAPI {

    @Autowired
    CookServiceImpl cookService;
    @Autowired
    private Environment environment;



    //pokaz wszystkich kucharzy/uzytkownikow
    @GetMapping()
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

    //dodawanie kucharza/uzytkownika
    @PostMapping()
    ResponseEntity<String> addCook(@Valid @RequestBody Cook cook) throws RecipeException{
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

    //usuwanie kucharza/uzytkownika
    @DeleteMapping("/{cookId}")
    public ResponseEntity<String> deleteCook(@PathVariable Integer cookId) throws RecipeException{
        try {
            cookService.deleteCook(cookId);
            String successMessage = environment.getProperty("API.DELETE_SUCCESS");
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    environment.getProperty(exception.getMessage()), exception);
        }
    }

}
