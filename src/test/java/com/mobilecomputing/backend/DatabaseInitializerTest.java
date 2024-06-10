package com.mobilecomputing.backend;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DatabaseInitializerTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private DatabaseInitializer databaseInitializer;

    @Test
    void initDatabase_shouldSaveRecipesFromCsvFile() {
        // When
        databaseInitializer.initDatabase();

        // Then
        //recipes.csv has exactly 550 recipes
        verify(recipeRepository, times(550)).save(any(Recipe.class));
    }
}

