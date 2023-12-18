package com.example.foodrecipeapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.foodrecipeapp.Models.Ingredient;


@Database(entities = {Ingredient.class},version = 1)
    public abstract class IngredientDataBase extends RoomDatabase {
        public abstract IngredientDao getDao();
    }



