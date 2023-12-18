package com.example.foodrecipeapp.Listeners;

import com.example.foodrecipeapp.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
   //void didFetch(List<InstructionsResponse> response, String message);

   // void didFetch(List<InstructionsResponse> response, String message);*/

    void didFetch(List<InstructionsResponse> response, String message);

    void didError(String message);
}
