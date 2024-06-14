# KitchenCompass Backend
The backend API for KitchenCompass, a recipe search and recommendation android app which I developed for my "mobile computing" course at reutlingen university. 
This service provides endpoints for searching recipes by ingredients, instructions or name, and retrieves detailed recipe information which I scraped from https://www.chefkoch.de/.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [API Endpoints](#api-endpoints)

## Features
- Search recipes by name, ingredients, or instructions.
- Fetch random recipes for inspiration.
- Provide detailed recipe information including image, ingredients and instructions.

## Technologies
- **Spring Boot**: The main framework for building the API.
- **H2**: Database for storing recipes.
- **JPA/Hibernate**: ORM for database interactions.

## API Endpoints

This section describes the available API endpoints for interacting with the recipe backend service. All endpoints return JSON-formatted responses and are accessible via HTTP. Base URL: http://localhost:9999/api

**Endpoints**
1. Retrieve Recipes
   GET /recipe

Retrieve a list of recipes based on optional query parameters. If no query parameters are provided, all recipes are returned.

    Parameters:
        name (optional): Search recipes by name.
        ingredients (optional): Search recipes by ingredients.
        instructions (optional): Search recipes by instructions.

    Usage:
        Retrieve recipes by name:
        GET /recipe?name=Spaghetti

        Retrieve recipes by ingredients:
        GET /recipe?ingredients=Tomato

        Retrieve recipes by instructions:
        GET /recipe?instructions=Boil

        Retrieve all recipes (no query parameters):
        GET /recipe

    Response:
        200 OK: A list of recipes matching the search criteria.
        500 Internal Server Error: An error occurred while processing the request.


2. Retrieve Random Recipes
   GET /random

Retrieve a list of random recipes.

    Usage:
        GET /random

    Response:
        200 OK: A list of random recipes.
        500 Internal Server Error: An error occurred while processing the request.


Error Handling

If an error occurs while processing a request, the API will return a 500 Internal Server Error status code. The response body will be empty.