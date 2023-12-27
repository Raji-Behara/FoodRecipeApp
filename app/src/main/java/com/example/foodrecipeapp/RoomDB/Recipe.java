package com.example.foodrecipeapp.RoomDB;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "recipe")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public int readyInMinutes;
    public int servings;

    public String image;


  public Recipe(int id, String title, int readyInMinutes,
                  int servings, String image

                 ) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;

     //   this.diets = diets;

     //   this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

  //  public String getImageType() {
     //   return imageType;
  //  }

  //  public ArrayList<String> getDiets()
   //     return diets;
  //  }

   // public String getInstructions() {
     //   return instructions;
  //  }

 //   public String getSpoonacularSourceUrl() {
        //return spoonacularSourceUrl;
  //  }

  //  public String instructions;

  //  public String spoonacularSourceUrl;
}
