package com.example.foodrecipeapp;



import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.analytics.FirebaseAnalytics;
//import com.google.firebase.auth.FirebaseAuth;
import com.example.foodrecipeapp.Adapters.IngredientsAdapter;
import com.example.foodrecipeapp.Adapters.InstructionsAdapter;
import com.example.foodrecipeapp.Adapters.SimilarRecipeAdapter;
//import com.example.foodrecipeapp.CustomOnClickListener;
import com.example.foodrecipeapp.Listeners.InstructionsListener;
import com.example.foodrecipeapp.Listeners.RecipeClickListener;
import com.example.foodrecipeapp.Listeners.RecipeDetailsListener;
import com.example.foodrecipeapp.Listeners.SimilarRecipesListener;
import com.example.foodrecipeapp.Loading_Animation.RecipeLoading;

import com.example.foodrecipeapp.Models.InstructionsResponse;
import com.example.foodrecipeapp.Models.RecipeDetailsResponse;
import com.example.foodrecipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE = 0.7f;

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };
    int id;

    TextView TextView_Meal_Name, textView_Meal_Source, textview_meal_Summary, textview_meal_Summary_Expand,
          textView_meal_servings, textView_meal_ready, textView_meal_price, ready_in, servings, healthy, instructions;
    ImageView ImageView_meal_image, vegeterian;
    RecyclerView recycler_meal_ingrediets, Recycler_meal_similar, Recycler_meal_instructions;
    NetworkManager manager;
    IngredientsAdapter ingredientsAdapter;
    private RecipeLoading recipeLoading;
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {

        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {

            textView_meal_ready.setText(response.readyInMinutes + " Minutes");
//            textView_meal_price.setText(response.pricePerServing + "$ Per Serving");
            textView_meal_servings.setText(response.servings + " Persons");
            TextView_Meal_Name.setText(response.title);
            textView_Meal_Source.setText(response.sourceName);
            textview_meal_Summary.setText(Html.fromHtml(response.summary));
            Picasso.get().load(response.image).into(ImageView_meal_image);
            recycler_meal_ingrediets.setHasFixedSize(true);
            recycler_meal_ingrediets.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recycler_meal_ingrediets.setAdapter(ingredientsAdapter);

            //to hide loading
            recipeLoading.hide();
            recipeLoading.cancel();
            recipeLoading.dismiss();
            recipeLoading.hide();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    SimilarRecipeAdapter similarRecipeAdapter;
    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            Recycler_meal_similar.setHasFixedSize(true);
            Recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, response, recipeClickListener);
            Recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    LinearLayout Layout_Expand;
    InstructionsAdapter instructionsAdapter;
    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            Recycler_meal_instructions.setHasFixedSize(true);
            Recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this, response);
            Recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_opener_image;
    LinearLayout contentView;
   // private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();
        navigationView();

        Layout_Expand.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new NetworkManager(this);
        manager.getRecipeDetials(recipeDetailsListener, id);
        manager.getSimilarRecipes(similarRecipesListener, id);
       manager.getInstructions(instructionsListener, id);

//        TO SHow loading
        recipeLoading.show();


    }


    private void findViews() {
        textView_meal_ready = findViewById(R.id.textView_meal_ready);
        textView_meal_servings = findViewById(R.id.textView_meal_servings);
        TextView_Meal_Name = findViewById(R.id.TextView_Meal_Name);
        textView_Meal_Source = findViewById(R.id.textView_Meal_Source);
        textview_meal_Summary = findViewById(R.id.textview_meal_Summary);
        ImageView_meal_image = findViewById(R.id.ImageView_meal_image);
        recycler_meal_ingrediets = findViewById(R.id.recycler_meal_ingrediets);
        Recycler_meal_similar = findViewById(R.id.Recycler_meal_similar);
        Layout_Expand = findViewById(R.id.Layout_Expand);
        textview_meal_Summary_Expand = findViewById(R.id.textview_meal_Summary_Expand);
        Recycler_meal_instructions = findViewById(R.id.Recycler_meal_instructions);
        menu_opener_image = findViewById(R.id.menu_opener_image);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.content);
//          Calling Loading
        recipeLoading = new RecipeLoading(this);
    }

    public void ExpandView(View view) {
        int v = (textview_meal_Summary.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(Layout_Expand, new AutoTransition());
        textview_meal_Summary.setVisibility(v);
    }

    //        Navigation Drawer Setting Start
    private void navigationView() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        menu_opener_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();


    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(getResources().getColor(R.color.dark_red));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private boolean checkInternet() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}