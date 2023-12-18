package com.example.foodrecipeapp;

import android.content.Context;

import androidx.room.Room;

import com.example.foodrecipeapp.Models.Ingredient;

import java.util.List;

public class DataBaseManager {

    interface DataBaseManagerInterfaceListener {
        void databaseGetListOfCities(List<Ingredient> l);
    }

    DataBaseManagerInterfaceListener listener;
    IngredientDataBase db;

    IngredientDataBase getDb(Context context) {
        if (db == null)
            db = Room.databaseBuilder(context,
                    IngredientDataBase.class, "database-Ingredients").build();

        return db;
    }

    void addCityInBGThread(Ingredient c){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().addNewIngredient(c);
            }
        });
    }
}