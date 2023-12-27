package com.example.foodrecipeapp.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    List<Recipe> getAll();

    @Insert
    void insertOneToDo(Recipe recipe);

    @Update
    void updateOneTodo(Recipe tobeupdated);

    @Delete
    void deleteOneToDo(Recipe todelete);


}

