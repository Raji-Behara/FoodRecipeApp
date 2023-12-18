package com.example.foodrecipeapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodrecipeapp.Models.Ingredient;
import com.example.foodrecipeapp.Models.Recipe;

import java.util.List;
@Dao
public interface IngredientDao {



    @Insert
    void addNewIngredient(Ingredient r);

    @Query("select * from Ingredient")

    List<Ingredient> getAllIngredient();

    /*@Query("select * from City where city like :c and country like :co")
    List<City> checkForCity(String c, String co);*/

    @Delete
    void deleteIngredient(Ingredient recipe);
}
