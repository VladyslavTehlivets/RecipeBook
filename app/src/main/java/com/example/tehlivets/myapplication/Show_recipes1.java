package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Show_recipes1 extends Activity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv_recipes;
    private LinearLayoutManager llm_recipes;
    private List<Recipe> l_recipes;
    private RVAdapter adapter;

    private String activeTag;
    Spinner tags;

    Cursor cursor;
    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        tags = (Spinner) findViewById(R.id.tagsSpinner);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addRecipe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NewRecipe.class);
                startActivity(intent);
            }
        });

        llm_recipes = new LinearLayoutManager(this.getApplicationContext());

        rv_recipes = (RecyclerView) findViewById(R.id.rv);
        rv_recipes.setHasFixedSize(true);
        rv_recipes.setLayoutManager(llm_recipes);

        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();

        initTagsSpinner();
        recipeListInit();

        adapter = new RVAdapter(dbAdapter, getApplicationContext(), l_recipes);
        rv_recipes.setAdapter(adapter);
    }

    private void initTagsSpinner() {
        List<String> tagsList = new ArrayList<String>();
        cursor = dbAdapter.rawQuery("select distinct name from tags order by name;");
        int tag = cursor.getColumnIndex("name");

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            tagsList.add(cursor.getString(tag));
        }
        ArrayAdapter<String> tagsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item
                , tagsList);
        tags.setAdapter(tagsAdapter);
        tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recipeListInit();
                adapter = new RVAdapter(dbAdapter, getApplicationContext(), l_recipes);
                rv_recipes.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void recipeListInit() {
        l_recipes = new ArrayList<>();
        cursor = dbAdapter.rawQuery("select max(id) from recipes");
        cursor.moveToFirst();
        int temp_id = cursor.getInt(0);
        Recipe.id = temp_id;

        String activeTag = tags.getSelectedItem().toString();
        cursor = dbAdapter.rawQuery("Select r.id, r.name, r.image, r.short_description from recipes r, tags t where r.id = t.rec_id and t.name = '" + activeTag + "' order by r.name");
        int recipe_name = cursor.getColumnIndex("r.name");
        int recipe_image = cursor.getColumnIndex("r.image");
        int recipe_description = cursor.getColumnIndex("r.short_description");
        int recipe_id = cursor.getColumnIndex("r.id");


        int id;
        String name = "";
        String description = "";

        byte[] photo = null;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            id = cursor.getInt(recipe_id);
            name = cursor.getString(recipe_name);
            photo = cursor.getBlob(recipe_image);
            description = cursor.getString(recipe_description);
            if (photo != null) {
                Bitmap decodedByte = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                l_recipes.add(new Recipe(name, description, decodedByte, id));
                photo = null;
            } else l_recipes.add(new Recipe(name, description, null, id));
        }
        cursor.close();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void finishShowRecipes() {
        this.finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent;
        if (id == R.id.show_recipes) {

        } else if (id == R.id.timer) {
            intent = new Intent(getBaseContext(), Cook.class);
            startActivity(intent);
        } else if (id == R.id.product_shoping) {
            intent = new Intent(getBaseContext(), Shoping.class);
            startActivity(intent);
        } else if (id == R.id.exit) {
            Show_recipes1.this.finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initTagsSpinner();
        recipeListInit();
        adapter = new RVAdapter(dbAdapter, getApplicationContext(), l_recipes);
        rv_recipes.setAdapter(adapter);
    }
}
