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

import java.util.ArrayList;

/**
 * Created by Tehlivets on 30/06/16.
 */
public class DeleteProduct extends Activity {
    private ListView lv_products;
    private String[] S_products;
    private TextView question;
    private TextView delete;

    Button deleteButton;

    Cursor cursor;
    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_product);
        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();
        lv_products = (ListView) findViewById(R.id.productsListView);
        delete = (TextView) findViewById(R.id.product_ifDelete);
        question = (TextView) findViewById(R.id.if_remove_text);
        deleteButton = (Button) findViewById(R.id.buttonRemove);
        deleteButton.setClickable(false);
        initListView();
        setOnItemClickListener();
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        super.onDestroy();
    }

    private void setOnItemClickListener() {
        lv_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                question.setText(R.string.if_remove);
                delete.setText(adapterView.getItemAtPosition(i).toString());
                deleteButton.setClickable(true);
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

    public void onClick(View view) {
        dbAdapter.execSQL("delete from product where product_name = '" + delete.getText() + "'");
        initListView();
        question.setText("");
        delete.setText("");
        deleteButton.setClickable(false);
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
