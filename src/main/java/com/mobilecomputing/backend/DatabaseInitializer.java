package com.mobilecomputing.backend;

import com.mobilecomputing.backend.model.Recipe;
import com.mobilecomputing.backend.repository.RecipeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private RecipeRepository recipeRepository;
    private boolean isInitialized = false;
    private static final String SEPARATOR = ";";
    private String path = "src/main/resources/recipes.csv";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        if(!isInitialized){
            initDatabase();
            isInitialized = true;
        }
    }

    private void initDatabase(){
        //csv layout:
        //[0] title, [1] image URL, [2] measurements, [3] ingredients, [4] total time, [5] instructions
        List<List<String>> entities = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                String[] recipeParts = line.split(SEPARATOR);
                String[] measurements = StringUtils.substringsBetween(recipeParts[2], "'", "'");
                String[] ingredients = StringUtils.substringsBetween(recipeParts[3], "'", "'");

                for(int i = 0; i < measurements.length; i++){
                    System.out.println("measurement: " + measurements[i]);
                }
                Recipe recipe = new Recipe(recipeParts[0], Arrays.toString(measurements) + " " + Arrays.toString(measurements),
                        recipeParts[4], recipeParts[5], recipeParts[1]);

                recipeRepository.save(recipe);

                System.out.println("Name: " + recipeParts[0]);
                System.out.println("Bild: " + recipeParts[1]);
                System.out.println("Mengenangaben: " + recipeParts[2]);
                System.out.println("Zutaten: " + recipeParts[3]);
                System.out.println("Bearbeitungszeit: " + recipeParts[4]);
                System.out.println("Anweisungen: " + recipeParts[5]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
