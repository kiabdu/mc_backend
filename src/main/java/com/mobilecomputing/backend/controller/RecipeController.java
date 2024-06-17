package com.mobilecomputing.backend.controller;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/random")
    public ResponseEntity<List<Recipe>> getRandomRecipes() {
        try {
            List<Recipe> recipes = recipeService.getRandomRecipes();
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getIngredientsContainingAll(@RequestParam List<String> arguments){
        try{
            List<Recipe> recipes;
            recipes = recipeService.findByIngredientsContainingAll(arguments);
            return ResponseEntity.ok(recipes);
        } catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }
}
