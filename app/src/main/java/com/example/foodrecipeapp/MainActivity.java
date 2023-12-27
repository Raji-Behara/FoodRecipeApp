package com.example.foodrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Models.Recipe;

import com.example.foodrecipeapp.RoomDB.DatabaseManager;
import com.google.android.material.navigation.NavigationView;

import com.example.foodrecipeapp.Adapters.RandomRecipeAdapter;
import com.example.foodrecipeapp.Listeners.RandomRecipesResponseListener;
import com.example.foodrecipeapp.Listeners.RecipeClickListener;
import com.example.foodrecipeapp.Loading_Animation.RecipeLoading;
import com.example.foodrecipeapp.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Recipe> list = new ArrayList<>(0);

    DatabaseManager dataBaseManager;

    //
    static final float END_SCALE = 0.7f;
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {
            // Intent i=new Intent(MainActivity.this,RecipeDetailsActivity.class);
            startActivity(new Intent(MainActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id", id));
        }
    };

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NetworkManager manager;

    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView RecyclerView;
    // SwipeRefreshLayout swipeRefreshLayout;
//    Loading
    private RecipeLoading recipeLoading;
    private final RandomRecipesResponseListener RandomRecipesResponseListener = new RandomRecipesResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {

            RecyclerView = findViewById(R.id.recycler_View);
            RecyclerView.setHasFixedSize(true);
            RecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes, recipeClickListener);
            RecyclerView.setAdapter(randomRecipeAdapter);
            //to hide loading
            recipeLoading.hide();
            recipeLoading.cancel();
            recipeLoading.dismiss();
            recipeLoading.hide();


            // Get database


        }


        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        }


    };


    Spinner spinner;
    List<String> tags = new ArrayList<>();
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(RandomRecipesResponseListener, tags);


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    };
    SearchView searchView;
    ImageView menu_opener_image;
    LinearLayout contentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.SearchView_Home);
        menu_opener_image = findViewById(R.id.menu_opener_image);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.content);
        spinner = findViewById(R.id.spinner_tags);

        recipeLoading = new RecipeLoading(this);

        navigationView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(RandomRecipesResponseListener, tags);
                //        TO SHow loading
                recipeLoading.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.tags, R.layout.spinner_txt
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_innerr_txt);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new NetworkManager(this);
    }

    private void navigationView() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.Navigation_bar_item_Home);
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

        drawerLayout.setScrimColor(getResources().getColor(R.color.dark_red));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

