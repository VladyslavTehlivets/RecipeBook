package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Tehlivets on 28/06/16.
 */

public class Start extends Activity {
    private String[] options;
    private ListView listView;

    static private final int EXIT = 5;
    static private final int NEW_RECIPE = 0;
    static private final int DELETE_RECIPE = 1;
    static private final int COOKING = 2;
    static private final int SHOW_RECIPES = 3;
    static private final int ADD_PRODUCT = 4;
    static private final int DELETE_PRODUCT = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        options = getResources().getStringArray(R.array.options);
        listView = (ListView) findViewById(R.id.optionsListView);
        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, options));
        setOnItemClickListener();

        Button exitButton = (Button) findViewById(R.id.btnNewAlertDialogButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(EXIT);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        return createAlertDialogWithButtons();
    }

    public void setOnItemClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int id, long j) {
                switch (id) {
                    case NEW_RECIPE:
                        intent = new Intent(getBaseContext(), NewRecipe.class);
                        startActivity(intent);
                        break;
                    case DELETE_RECIPE:
                        intent = new Intent(getBaseContext(), RemoveRecipe.class);
                        startActivity(intent);
                        break;
                    case COOKING:
                        intent = new Intent(getBaseContext(), Cook.class);
                        startActivity(intent);
                        break;
                    case SHOW_RECIPES:
                        intent = new Intent(getBaseContext(), Show_recipes.class);
                        startActivity(intent);
                        break;
                    case ADD_PRODUCT:
                        intent = new Intent(getBaseContext(), AddProduct.class);
                        startActivity(intent);
                        break;
                    case DELETE_PRODUCT:
                        intent = new Intent(getBaseContext(), DeleteProduct.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle(getString(R.string.exit));
        dialogBuilder.setMessage(getString(R.string.areYouSure));
        dialogBuilder.setCancelable(false);

        dialogBuilder.setPositiveButton(getString(R.string.yes), new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast(getString(R.string.exit));
                Start.this.finish();
            }
        });
        dialogBuilder.setNegativeButton(getString(R.string.no), new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        return dialogBuilder.create();
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
