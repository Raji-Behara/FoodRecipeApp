package com.example.foodrecipeapp;

import android.content.Context;

import androidx.room.Room;

import com.example.foodrecipeapp.Models.Ingredient;

import java.util.List;

public class DataBaseManager {

    interface DataBaseManagerInterfaceListener {
        void databaseGetListOfIngredients(List<Ingredient> l);
    }

    DataBaseManagerInterfaceListener listener;
    IngredientDataBase db;

    IngredientDataBase getDb(Context context) {
        if (db == null)
            db = Room.databaseBuilder(context,
                    IngredientDataBase.class, "databaseIngredients").build();

        return db;
    }

    void addIngredientsInBGThread(Ingredient c){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().addNewIngredient(c);
            }
        });
    }


    void getAllIngredientsInBGThread(){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Ingredient> list =  db.getDao().getAllIngredient();
                MyApp.mainhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // main thread
                        listener.databaseGetListOfIngredients(list);
                    }
                });

            }
        });
    }
}