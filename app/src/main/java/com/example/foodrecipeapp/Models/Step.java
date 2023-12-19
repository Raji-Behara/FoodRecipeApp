package com.example.foodrecipeapp.Models;
import java.util.ArrayList;

public class Step {
    public int number;
    public String step;
    public ArrayList<Ingredient> ingredients;
    public ArrayList<Equipment> equipment;

    public int getNumber() {
        return number;
    }

    public String getStep() {
        return step;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public Step(int number, String step, ArrayList<Ingredient> ingredients, ArrayList<Equipment> equipment) {
        this.number = number;
        this.step = step;
        this.ingredients = ingredients;
        this.equipment = equipment;


    }
}
