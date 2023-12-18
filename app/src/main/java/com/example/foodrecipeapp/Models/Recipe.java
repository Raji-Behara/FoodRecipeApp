package com.example.foodrecipeapp.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

public class Recipe {
//ArrayList<Recipe>recipeArrayList;



   // public ArrayList<ExtendedIngredient> extendedIngredients;
    public int id;
    public String title;
    public int readyInMinutes;
    public int servings;

    public String image;
    public String imageType;

   public ArrayList<String> diets;

    public Recipe(int id, String title, int readyInMinutes,
                  int servings, String image, String imageType,
                  ArrayList<String> diets, String instructions,
                  String spoonacularSourceUrl) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;
        this.imageType = imageType;
        this.diets = diets;
        this.instructions = instructions;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public int getId() {
  return id;
 }

 public String getTitle() {
  return title;
 }

 public int getReadyInMinutes() {
  return readyInMinutes;
 }

 public int getServings() {
  return servings;
 }

 public String getImage() {
  return image;
 }

 public String getImageType() {
  return imageType;
 }

 public ArrayList<String> getDiets() {
  return diets;
 }

 public String getInstructions() {
  return instructions;
 }

 public String getSpoonacularSourceUrl() {
  return spoonacularSourceUrl;
 }

 public String instructions;

    public String spoonacularSourceUrl;
}
