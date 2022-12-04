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

    //pobierz wszystkich kucharzy
    public List<Cook> getAllCooks() throws RecipeException {
        List<Cook> cooks = cookRepository.findAll();
        if(cooks.isEmpty()) throw  new RecipeException("Service.COOK_NOT_FOUND");
        return cooks;
    }

    //dodaj kucharza
    public Integer addCook(Cook cook) throws RecipeException{
        Cook cook1 = new Cook();
        cook1.setId(cook.getId());
        cook1.setAuthor(cook.getAuthor());
        cookRepository.save(cook1);
        return cook1.getId();
    }

    //usun kucharza
    public void deleteCook(Integer cookId) throws RecipeException{
        Optional<Cook> optional = cookRepository.findById(cookId);
        Cook cook = optional.orElseThrow(()->new RecipeException("Service.COOK_NOT_FOUND"));
        cookRepository.delete(cook);
    }











}
