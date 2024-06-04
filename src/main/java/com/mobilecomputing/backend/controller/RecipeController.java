package com.mobilecomputing.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RecipeController {

    @GetMapping("/get/recipe")
    public String getRecipe() {
        return "Hello World";
    }

    //TODO: get recipe by id

    //TODO: get recipe by name

    //TODO: get recipe by ingredients
}
