package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tehlivets on 29/06/16.
 */

public class NewRecipe extends Activity {

    String[] S_products;
    Button addRecipe;
    Button addFoto;
    Button changeFoto;
    Button addProduct;

    boolean isTitleEnabled = false;
    boolean isQuantityEnabled = false;

    EditText productQuantity;
    EditText titleEdit;

    private Spinner spinner;

    Cursor cursor;
    DbAdapter dbAdapter;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Intent intent;
    private File imagesFolder;
    private File image;
    private Uri uriSavedImage;

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    private int quantity;
    private String recipeName;
    private String productName;
    private String photopath;
    int recipe_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);

        productQuantity = (EditText) findViewById(R.id.recipe_quantity_edit);
        titleEdit = (EditText) findViewById(R.id.recipe_title);
        addRecipe = (Button) findViewById(R.id.addRecipeButton);
        addFoto = (Button) findViewById(R.id.addFoto);
        changeFoto = (Button) findViewById(R.id.buttonChangeFoto);
        changeFoto.setClickable(false);
        addProduct = (Button) findViewById(R.id.add_product);

        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();

        imagesFolder = new File(Environment.getExternalStorageDirectory(), getString(R.string.RecipeImages));
        imagesFolder.mkdirs();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        setS_products();
        setShakeListener();
        setSpiner();
        waitForInput();
    }

    private void setS_products() {
        cursor = dbAdapter.rawQuery("Select product_name from product order by product_name");
        int product_name = cursor.getColumnIndex("product_name");
        String data = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            data += cursor.getString(product_name) + ",";
        }
        S_products = data.split(",");
        cursor.close();
    }

    private void setShakeListener() {
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                productQuantity.setText("");
                titleEdit.setText("");
                isQuantityEnabled = false;
                isTitleEnabled = false;
                photopath = imagesFolder + "";
            }
        });
    }

    private void setSpiner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, S_products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                productName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void waitForInput() {
        titleEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isTitleEnabled = true;
                recipeName = titleEdit.getText().toString();
                chekButtonTitle();
            }
        });
        productQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isQuantityEnabled = true;
                chekButtonQuantity();
            }
        });
    }

    private void chekButtonQuantity() {
        try {
            quantity = Integer.parseInt(productQuantity.getText().toString());
            if (isQuantityEnabled && isTitleEnabled) {
                addProduct.setClickable(true);
            }
        } catch (NumberFormatException e) {
            showToast("Enter numerical value");
        }
    }

    private void chekButtonTitle() {
        if (isTitleEnabled) {
            if (!addFoto.isClickable()) {
                addFoto.setClickable(true);
                changeFoto.setClickable(false);
            }
            addRecipe.setClickable(true);
            addRecipe.setText(getString(R.string.add) + " " + recipeName);
        }
    }

    public void addRecipe(View view) {
        if (photopath.equals(imagesFolder + "")) {
            showToast("Please Make a picture...");
            showToast("Fotopath is " + photopath);
        } else {
            ContentValues cv = new ContentValues();
            cv.put("recipe_name", recipeName);
            cv.put("photo_path", photopath);
            long row = dbAdapter.insert("recipe", cv);
            showToast("Inserted row " + row);
            cursor = dbAdapter.rawQuery("select id from recipe where recipe_name = '" + recipeName + "'");
            int id = cursor.getColumnIndex("id");
            cursor.moveToFirst();
            String recipe_id_S = cursor.getString(id);
            recipe_id = Integer.parseInt(recipe_id_S);
            showToast("id " + id + "rec_id " + recipe_id);
            addRecipe.setClickable(false);
            photopath = imagesFolder + "";
        }
    }

    public void addProduct(View view) {
        ContentValues cv = new ContentValues();
        String myQuery = "select id from product where product_name = '" + productName + "'";
        cursor = dbAdapter.rawQuery(myQuery);
        int product_Id = cursor.getColumnIndex("id");
        cursor.moveToFirst();
        String product_id = cursor.getString(product_Id);
        showToast(productName + " " + cursor.getString(product_Id) + " " + recipe_id);
        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                cv.put("rec_id", recipe_id);
                cv.put("product_id", product_id);
                cv.put("count", "" + quantity);
                long row = dbAdapter.insert("recipe_products", cv);
                showToast("Inserted row " + row);
            }
        } catch (Exception e) {
            showToast("Exception Baza");
        }
    }

    public void makeFoto(View view) {
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        image = new File(imagesFolder, "" + recipeName + ".jpg");
        uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        photopath = "" + imagesFolder + "/" + recipeName + ".jpg";
        addFoto.setClickable(false);
        changeFoto.setClickable(true);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }


    public void changeFoto(View view) {
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        image = new File(imagesFolder, "" + recipeName + ".jpg");
        uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        photopath = "" + imagesFolder + "/" + recipeName + ".jpg";
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(bundle, outPersistentState);
        bundle.putString("photopath", photopath);
        bundle.putString("recipeName", recipeName);
        bundle.putString("imagesFolder", imagesFolder + "");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
