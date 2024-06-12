package com.mobilecomputing.backend;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.json.JSONArray;
import org.json.JSONObject;

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

    protected void initDatabase() {
        InputStream inputStream = DatabaseInitializer.class.getResourceAsStream("/recipes.json");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }

            // Parse the JSON content
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Iterate over each JSON object
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String ingredients = jsonObject.getString("ingredients");
                String instructions = jsonObject.getString("instructions");
                String estimatedTime = jsonObject.getString("estimated_time");
                String previewImage = jsonObject.getString("previewImage");

                // Create Recipe object and save it
                Recipe recipe = new Recipe(name, ingredients, instructions, estimatedTime, previewImage);
                recipeRepository.save(recipe);
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
