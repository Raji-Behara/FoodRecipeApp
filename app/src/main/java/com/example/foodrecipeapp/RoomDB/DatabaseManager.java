package com.example.foodrecipeapp.RoomDB;

import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import com.example.foodrecipeapp.Listeners.RandomRecipesResponseListener;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {
    interface DatabaseListener{
        void databaseManagerFinishWithListOfToDos(List<Recipe> list);
        void databaseManagerFinishSearchingFoToDos(List<Recipe> list);

    }

    DatabaseListener listener;
    RecipeDatabase db;
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Handler mainLooperHandler = new Handler(Looper.getMainLooper());
    public RecipeDatabase getDatabaseInstance(RandomRecipesResponseListener context) {
        if (db == null) {
          //  db = Room.databaseBuilder(context,
            //        RecipeDatabase.class, "database-ToDo").build();



        }
        return  db;
    }

    void insertOneToDoInBackgroundThread(RecipeDatabase toDo){
        // run the DAO query in background thread.
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // will run in bg thread
              //  db.getrd().insertOneToDo(toDo);
                // no notification is needed.
            }
        });
    }

    void getAllToDosFromDatabaseInBackgroundThread(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Recipe> list =  db.getrd().getAll(); //2
                // switch from background thread to main thread
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // send any messages to main thread
                        listener.databaseManagerFinishWithListOfToDos(list);
                    }
                });
            }
        });

    }





    void deleteOneToDoInBackgroundThread(Recipe tobedeleted){
        // run the DAO query in background thread.
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // will run in bg thread
                db.getrd().deleteOneToDo(tobedeleted);
                // no notification is needed.
            }
        });
    }







}
