package com.mobilecomputing.backend.model;

public class Recipe
{
    private int id;
    private String name;
    private Instructions instructions;

    public Recipe(){

    }

    public Recipe(int id, String name, Instructions instructions){
        this.id = id;
        this.name = name;
        this.instructions = instructions;
    }


}
