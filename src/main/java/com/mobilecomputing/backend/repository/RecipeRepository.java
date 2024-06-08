package com.mobilecomputing.backend.repository;

import com.mobilecomputing.backend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByNameContaining(String name);
    List<Recipe> findByInstructionsContaining(String instructions);
    List<Recipe> findByIngredientsContaining(String ingredients);
}
