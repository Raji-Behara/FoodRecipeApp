package com.example.foodrecipeapp.Listeners;

import com.example.foodrecipeapp.Models.RandomRecipeApiResponse;

public interface RandomRecipesResponseListener {


   /* void didFetch(RandomRecipeApiResponse response, String message);*/

    //void didFetch(RandomRecipeApiResponse response, String message);

    void didFetch(RandomRecipeApiResponse response, String message);

    void didError(String message);
}
