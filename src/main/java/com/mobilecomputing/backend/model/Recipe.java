package com.mobilecomputing.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(columnDefinition = "TEXT")
    private String estimated_time;

    @Column(columnDefinition = "VARCHAR(255)")
    private String previewImage;

    //@Column(name = "review")
    //private int review;

    //@Column(name = "category")
    //private String category;

    public Recipe(){

    }

    public Recipe(String name, String ingredients,
                  String instructions, String estimated_time,
                  String previewImage) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.estimated_time = estimated_time;
        this.previewImage = previewImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(String estimated_time) {
        this.estimated_time = estimated_time;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    /*
    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
    */

    /*
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
     */
}
