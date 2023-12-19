package com.example.foodrecipeapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.example.foodrecipeapp.Models.Recipe;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {

    public static Handler mainhandler;
    DataBaseManager databaseManager = new DataBaseManager();

    ArrayList<Recipe> listofrecipies = new ArrayList<>(0);
    static ExecutorService executorService = Executors.newFixedThreadPool(4);
    static Handler mainLooperHandler = new Handler(Looper.getMainLooper());
}
