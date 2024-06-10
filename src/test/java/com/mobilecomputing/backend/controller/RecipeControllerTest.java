package com.mobilecomputing.backend.controller;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    void getAllRecipes_shouldReturnAllRecipes_whenNameIsNull() {
        // Given
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe 1", "Ingredients 1", "Instructions 1", "30 min", "image1.jpg"),
                new Recipe("Recipe 2", "Ingredients 2", "Instructions 2", "45 min", "image2.jpg")
        );
        when(recipeRepository.findAll()).thenReturn(recipes);

        // When
        ResponseEntity<List<Recipe>> response = recipeController.getAllRecipes(null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipes, response.getBody());
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findByNameContaining(anyString());
    }

    @Test
    void getAllRecipes_shouldReturnRecipesByName_whenNameIsProvided() {
        // Given
        String name = "Recipe";
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe 1", "Ingredients 1", "Instructions 1", "30 min", "image1.jpg"),
                new Recipe("Recipe 2", "Ingredients 2", "Instructions 2", "45 min", "image2.jpg")
        );
        when(recipeRepository.findByNameContaining(name)).thenReturn(recipes);

        // When
        ResponseEntity<List<Recipe>> response = recipeController.getAllRecipes(name);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipes, response.getBody());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findByNameContaining(name);
    }

    @Test
    void getRecipeByName_shouldReturnRecipesByName() {
        // Given
        String name = "Recipe";
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe 1", "Ingredients 1", "Instructions 1", "30 min", "image1.jpg"),
                new Recipe("Recipe 2", "Ingredients 2", "Instructions 2", "45 min", "image2.jpg")
        );
        when(recipeRepository.findByNameContaining(name)).thenReturn(recipes);

        // When
        ResponseEntity<List<Recipe>> response = recipeController.getRecipeByName(name);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipes, response.getBody());
        verify(recipeRepository, times(1)).findByNameContaining(name);
    }

    // Similar tests for getRecipeByInstructions and getRecipeByIngredients methods
}

