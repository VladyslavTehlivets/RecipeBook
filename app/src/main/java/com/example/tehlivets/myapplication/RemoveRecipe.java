package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tehlivets on 29/06/16.
 */
public class RemoveRecipe extends Activity {

    private ListView lv_recipes;
    private String[] S_recipes;
    private TextView question;
    private TextView delete;
    Button removeButton;

    Cursor cursor;
    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_recipe);
        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();
        lv_recipes = (ListView) findViewById(R.id.recipesListView);
        delete = (TextView) findViewById(R.id.recipe_ifDelete);
        question = (TextView) findViewById(R.id.if_remove_text1);
        removeButton = (Button) findViewById(R.id.buttonRemoveRecipe);
        removeButton.setClickable(false);
        initListView();
        setOnItemClickListener();
    }

    private void initListView() {
        cursor = dbAdapter.rawQuery("Select recipe_name from recipe order by recipe_name");
        int recipe_name = cursor.getColumnIndex("recipe_name");
        String data = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            data += cursor.getString(recipe_name) + ",";
        }
        S_recipes = data.split(",");
        lv_recipes.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, S_recipes));
        cursor.close();
    }

    private void setOnItemClickListener() {
        lv_recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                question.setText(R.string.if_remove);
                delete.setText(adapterView.getItemAtPosition(i).toString());
                removeButton.setClickable(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void removeRecipe(View view) {
        cursor = dbAdapter.rawQuery("select id from recipe where recipe_name = '" + delete.getText() + "'");
        int id = cursor.getColumnIndex("id");
        cursor.moveToFirst();
        String recipe_id_S = cursor.getString(id);
        int recipe_id = Integer.parseInt(recipe_id_S);

        dbAdapter.execSQL("delete from recipe_products where rec_id = " + recipe_id + "");
        dbAdapter.execSQL("delete from recipe where recipe_name = '" + delete.getText() + "'");

        initListView();
        showToast("Deleted "+ delete.getText());
        question.setText("");
        delete.setText("");
        removeButton.setClickable(false);
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
