package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Shoping extends Activity {

    DbAdapter dbAdapter;
    Cursor cursor;

    private ListView lv_products;

    private ArrayList<String> products = new ArrayList<>();
    private ArrayList<String> recipes = new ArrayList<>();
    private String[] recipesToDialog;
    private boolean[] checked;

    AlertDialog selectRecipes;
    private ArrayList<String> selectedRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoping);

        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();
        lv_products = (ListView) findViewById(R.id.productsAddListView);

        initRecipesListView();
        initCheckedListView();
        buildDialog();
        initProductListView();
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_for_shopping);
        builder.setMultiChoiceItems(recipesToDialog, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked == false) {
                    ContentValues cv = new ContentValues();
                    cv.put("mark",0);
                    dbAdapter.update("recipes", cv, "name=" + "'"+recipesToDialog[which]+"'");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put("mark",1);
                    dbAdapter.update("recipes", cv, "name=" + "'"+recipesToDialog[which]+"'");
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initProductListView();
            }
        });

        selectRecipes = builder.create();
    }

    private void initCheckedListView() {
        cursor = dbAdapter.rawQuery("select * from recipes order by name asc;");
        int mark = cursor.getColumnIndex("mark");
        checked = new boolean[recipes.size()];
        int i = 0;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            checked[i] = cursor.getInt(mark) > 0;
            i++;
        }
    }

    private void initRecipesListView() {
        cursor = dbAdapter.rawQuery("select name from recipes order by name asc;");
        int recipe_name = cursor.getColumnIndex("name");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            recipes.add(cursor.getString(recipe_name));
        }
        recipesToDialog = recipes.toArray(new String[0]);
        cursor.close();
    }

    private void initProductListView() {
        products.removeAll(products);
        cursor = dbAdapter.rawQuery("select p.name from products p, recipes r where p.rec_id = r.id and r.mark='1'" +
                "order by p.name asc;");
        int product_name = cursor.getColumnIndex("name");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            products.add(cursor.getString(product_name));
        }
        lv_products.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_checked, products));
        cursor.close();
    }

    public void selectRecipeProducts(View view) {
        selectRecipes.show();
    }

    public void closeShoping(View view) {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
