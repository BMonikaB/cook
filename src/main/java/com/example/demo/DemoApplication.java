package com.example.demo;

import com.example.demo.domain.Cook;
import com.example.demo.domain.Recipe;
import com.example.demo.exception.RecipeException;
import com.example.demo.repository.CookRepository;
import com.example.demo.service.CookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	@Autowired
	CookServiceImpl cookService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		add();
		addRecipeToCook();
	}

	public void add() throws RecipeException {
		Cook cook1 = new Cook("anna@gmail.com");
		Cook cook2 = new Cook("karolina14@gmail.com");
		Cook cook3 = new Cook("zuznan16@gmail.com");

		cookService.addCook(cook1);
		cookService.addCook(cook2);
		cookService.addCook(cook3);





	}

	public void addRecipeToCook() throws RecipeException {

		Recipe recipe1 = new Recipe("spaghetti","Tu znajduje się przepis na zrobienie spagetti");
		Recipe recipe2 = new Recipe("kotlet schabowy","Tutaj znajduje sie przepis na kotlet schabowy");

		cookService.addRecipeIfCookExisting(1,recipe1);
		cookService.addRecipeIfCookExisting(2,recipe2);


	}
}
