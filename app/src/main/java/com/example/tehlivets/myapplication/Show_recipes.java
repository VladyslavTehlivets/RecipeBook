package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Tehlivets on 29/06/16.
 */
public class Show_recipes extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor mSensor;

    private String[] S_recipes;

    private String[] S_recipe;
    private ListView recipe_products;

    Cursor cursor;
    DbAdapter dbAdapter;

    private String recipe_name;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);
        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        recipe_products = (ListView) findViewById(R.id.recipe_products);
        init_S_recipes_View();
        setSpiner();
    }

    private void init_S_recipes_View() {
        cursor = dbAdapter.rawQuery("Select recipe_name from recipe order by recipe_name");
        int recipe_name = cursor.getColumnIndex("recipe_name");
        String data = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            data += cursor.getString(recipe_name) + ",";
        }
        S_recipes = data.split(",");
        cursor.close();
    }

    private void initProductList() {
        cursor = dbAdapter.rawQuery("Select p.product_name, rp.count from product p,recipe_products" +
                " rp,recipe r where rp.rec_id = r.id and p.id = rp.product_id and r.recipe_name = '" + recipe_name + "' order by 1");
        int product_name = cursor.getColumnIndex("product_name");
        int product_count = cursor.getColumnIndex("count");
        String data = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            data += cursor.getString(product_name) + " ";
            data += cursor.getString(product_count) + ",";
        }
        S_recipe = data.split(",");
        recipe_products.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, S_recipe));
        cursor.close();
    }

    private void setSpiner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, S_recipes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                recipe_name = parent.getItemAtPosition(position).toString();
                initProductList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        setContentView(R.layout.market);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
