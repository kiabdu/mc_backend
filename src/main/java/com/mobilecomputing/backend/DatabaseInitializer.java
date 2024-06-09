package com.mobilecomputing.backend;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RecipeRepository recipeRepository;
    private boolean isInitialized = false;
    private static final String SEPARATOR = ";";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        init();
    }

    public void init() {
        if (!isInitialized) {
            initDatabase();
            isInitialized = true;
        }
    }

    private void initDatabase() {
        InputStream inputStream = DatabaseInitializer.class.getResourceAsStream("/recipes.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] recipeParts = line.split(SEPARATOR);
                String[] measurements = StringUtils.substringsBetween(recipeParts[2], "'", "'");
                String[] ingredients = StringUtils.substringsBetween(recipeParts[3], "'", "'");

                StringBuilder measurementsAndIngredients = new StringBuilder();

                for (int i = 0; i < measurements.length; i++) {
                    measurementsAndIngredients.append(measurements[i]).append(" ").append(ingredients[i]).append(" | ");
                }

                // csv layout:
                // [0] title, [1] image URL, [2] measurements, [3] ingredients, [4] total time, [5] instructions
                Recipe recipe = new Recipe(recipeParts[0], measurementsAndIngredients.toString(),
                        recipeParts[5], recipeParts[4], recipeParts[1]);

                recipeRepository.save(recipe);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
