package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Tehlivets on 30/06/16.
 */
public class AddProduct extends Activity {

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    EditText productName;
    Button addButton;
    DbAdapter dbAdapter;
    Cursor cursor;
    private ListView lv_products;
    private String[] S_products;
    ArrayList<String> AL_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        productName = (EditText) findViewById(R.id.product_enter);
        addButton = (Button) findViewById(R.id.addButton);
        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();
        lv_products = (ListView) findViewById(R.id.productsAddListView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        AL_products = new ArrayList<String>();
        initListView();
        setShakeListener();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                if (!productName.getText().toString().equals("")) {
                    String product_name = productName.getText().toString();
                    cv.put("product_name", product_name);
                    dbAdapter.insert("product", cv);
                    showToast(getString(R.string.product_added) + " " + product_name);
                    productName.setText("");
                    initListView();
                } else showToast("Please Enter Something...");
            }
        });
    }

    private void initListView() {
        cursor = dbAdapter.rawQuery("Select product_name from product order by product_name");
        int product_name = cursor.getColumnIndex("product_name");
        String data = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            data += cursor.getString(product_name) + ",";
        }
        S_products = data.split(",");
        lv_products.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_expandable_list_item_1, S_products));
        cursor.close();
    }

    private void setShakeListener() {
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                productName.setText("");
            }
        });
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
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}
