package com.mobilecomputing.backend.repository;

import com.mobilecomputing.backend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Recipe> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.instructions) LIKE LOWER(CONCAT('%', :instructions, '%'))")
    List<Recipe> findByInstructionsContainingIgnoreCase(@Param("instructions") String instructions);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :ingredients, '%'))")
    List<Recipe> findByIngredientsContainingIgnoreCase(@Param("ingredients") String ingredients);

    //@Query("SELECT r FROM Recipe r WHERE " + "(:ingredients) MEMBER OF r.ingredients")
    //List<Recipe> findByIngredientsContainingAll(@Param("ingredients") List<String> ingredients);

}
