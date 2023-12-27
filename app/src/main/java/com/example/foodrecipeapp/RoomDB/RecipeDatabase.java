package com.example.foodrecipeapp.RoomDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class}, exportSchema = false, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao getrd();
}
