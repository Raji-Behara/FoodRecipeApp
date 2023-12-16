package com.example.foodrecipeapp.Listeners;

import com.example.foodrecipeapp.Models.SimilarRecipeResponse;
import com.example.foodrecipeapp.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
   /*void didFetch(List<SimilarRecipeResponse> response, String message);

    void didFetch(List<SimilarRecipeResponse> response, String message);*/

    void didFetch(List<SimilarRecipeResponse> response, String message);

    void didError(String message);
}
