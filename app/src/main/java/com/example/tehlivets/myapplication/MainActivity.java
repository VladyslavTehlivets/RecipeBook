package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.lang.*;

public class MainActivity extends Activity {

    DbAdapter dbAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();

        addPhotos();
        addStepPhotots();

        Intent intent = new Intent(this, Show_recipes1.class);
        startActivity(intent);
        finish();
    }

    private void addStepPhotots() {
        cursor = dbAdapter.rawQuery("select image from steps where rec_id='1' and number_of_step='1';");
        cursor.moveToFirst();

        byte[] bitmapdata = cursor.getBlob(0);
        if (bitmapdata == null) {
            ContentValues cv = new ContentValues();
            int number_of_step;
            for (int i = 1; i <= 26; i++) {
                cursor = dbAdapter.rawQuery("select * from steps where rec_id = '" + i + "'");
                number_of_step = cursor.getColumnIndex("number_of_step");
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    int stepNumber = cursor.getInt(number_of_step);
                    String stepNum = "step_" + i + "_" + stepNumber;
                    int id = getResources().getIdentifier(stepNum, "drawable", getPackageName());
                    Bitmap stepPhoto = BitmapFactory.decodeResource(getResources(), id);
                    bitmapdata = getBitmapAsByteArray(stepPhoto);
                    cv.put("image", bitmapdata);
                    dbAdapter.update("steps", cv, "rec_id=" + "'" + i + "' and number_of_step='" + stepNumber + "'");
                    cv.clear();
                }
            }
        }
    }

    private void addPhotos() {
        cursor = dbAdapter.rawQuery("select image from recipes where id='1';");
        cursor.moveToFirst();

        byte[] bitmapdata = cursor.getBlob(0);
        if (bitmapdata == null) {
            ContentValues cv = new ContentValues();
            for (int i = 1; i <= 26; i++) {
                String recipeNumer = "recipe_" + i;
                int id = getResources().getIdentifier(recipeNumer, "drawable", getPackageName());
                Bitmap recipePhoto = BitmapFactory.decodeResource(getResources(), id);
                bitmapdata = getBitmapAsByteArray(recipePhoto);
                cv.put("image", bitmapdata);
                dbAdapter.update("recipes", cv, "id=" + "'" + i + "'");
                cv.clear();
            }
        }
    }

    public byte[] getBitmapAsByteArray(Bitmap img) {
        if (img != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            return outputStream.toByteArray();
        } else return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

