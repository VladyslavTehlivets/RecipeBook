package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends Activity {

    Recipe recipe = new Recipe();
    DbAdapter dbAdapter;

    Bitmap recipeImage = null;
    Bitmap stepImage = null;

    TextView recipePhotoText, stepPhotoTekst;
    EditText addTag, addProduct, recipeName, recipeDescription;
    List<String> tags = new ArrayList<>();
    List<String> products = new ArrayList<>();
    List<Step> steps = new ArrayList<>();
    ListView tags_lv, products_lv, steps_lv;

    Button addStepOk, addStepCancel;
    ImageButton addStepUpload, recipeUpload;


    Dialog addStep;
    EditText stepDescription;
    static final int ID_ADD_STEP_DIALOG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);

        dbAdapter = new DbAdapter(this, getString(R.string.database)).open();

        showToast(recipe.getId()+"");
        recipeName = (EditText) findViewById(R.id.recipeName);
        recipeDescription = (EditText) findViewById(R.id.recipeDescription);

        recipePhotoText = (TextView) findViewById(R.id.recipePhotoText);
        recipeUpload = (ImageButton) findViewById(R.id.recipeUpload);

        tags_lv = (ListView) findViewById(R.id.tags);
        products_lv = (ListView) findViewById(R.id.products);
        steps_lv = (ListView) findViewById(R.id.steps);

        addTag = (EditText) findViewById(R.id.addTag);
        addProduct = (EditText) findViewById(R.id.addProduct);
    }

    public void addPhoto(View view) {
        if (view.getId() == R.id.recipeUpload) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);
        } else {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    Uri stepImageUri  = imageReturnedIntent.getData();
                    try {
                        stepImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),stepImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stepPhotoTekst.setText(stepImageUri.getLastPathSegment());
                    addStepUpload.setImageResource(R.mipmap.delete_item);
                    addStepUpload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stepPhotoTekst.setText(R.string.AddPicture);
                            addStepUpload.setImageResource(R.mipmap.upload);
                            addStepUpload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    addPhoto(v);
                                }
                            });
                        }
                    });
                    break;
                case 1:
                    Uri recipeImageUri = imageReturnedIntent.getData();
                    try {
                        recipeImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),recipeImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recipePhotoText.setText(recipeImageUri.getLastPathSegment());
                    recipeUpload.setImageResource(R.mipmap.delete_item);
                    recipeUpload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recipePhotoText.setText(R.string.AddPicture);
                            recipeUpload.setImageResource(R.mipmap.upload);
                            recipeUpload.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    addPhoto(v);
                                }
                            });
                        }
                    });
                    break;
            }
        }
    }

    public void addTag(View view) {
        if (addTag.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Enter Some Value", Toast.LENGTH_SHORT).show();
        } else {
            tags.add(addTag.getText().toString());
            addTag.setText("");
            tags_lv.setAdapter(new StringAdapterWithDeleteButton(getBaseContext(), R.layout.list_with_delete, tags));
        }
    }

    public void addProduct(View view) {
        if (addProduct.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Enter Some Value", Toast.LENGTH_SHORT).show();
        } else {
            products.add(addProduct.getText().toString());
            addProduct.setText("");
            products_lv.setAdapter(new StringAdapterWithDeleteButton(getBaseContext(), R.layout.list_with_delete, products));
        }
    }

    public void addStep(View view) {
        showDialog(ID_ADD_STEP_DIALOG);
        addStep.setTitle("Add Step " + (steps.size() + 1));
    }

    protected Dialog onCreateDialog(int id) {
        addStep = new Dialog(this);
        addStep.setContentView(R.layout.add_step_dialog);
        addStep.setTitle("Add Step " + (steps.size() + 1));
        addStep.setCancelable(false);

        stepPhotoTekst = (TextView) addStep.findViewById(R.id.stepPhotoTekst);
        addStepOk = (Button) addStep.findViewById(R.id.Ok);
        addStepCancel = (Button) addStep.findViewById(R.id.Cancel);
        addStepUpload = (ImageButton) addStep.findViewById(R.id.stepUpload);
        stepDescription = (EditText) addStep.findViewById(R.id.stepDescription);

        addStepUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoto(v);
            }
        });

        addStepOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step step = new Step(steps.size() + 1);
                String description = stepDescription.getText().toString();
                Bitmap img = null;

                if (description.equals("")) {
                    Toast.makeText(getBaseContext(), "Enter Some Value", Toast.LENGTH_SHORT).show();
                } else {
                    step.setDescription(description);
                    if (stepImage != null) {
                        step.setImg(stepImage);
                    }
                    steps.add(step);
                    steps_lv.setAdapter(new StepAdapterWithDeleteButton(getBaseContext(), R.layout.list_with_delete, steps));
                    stepDescription.setText("");

                    stepPhotoTekst.setText(R.string.AddPicture);
                    addStepUpload.setImageResource(R.mipmap.upload);
                    addStepUpload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addPhoto(v);
                        }
                    });

                    addStep.dismiss();
                }
            }
        });

        addStepCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStep.cancel();
            }
        });
        return addStep;
    }

    public void closeActivity(View view) {
        Recipe.id--;
        this.finish();
    }

    public void onSubmit(View view) throws IOException {
        recipe.setName(recipeName.getText().toString());
        recipe.setDesctription(recipeDescription.getText().toString());
        if (recipeImage != null) {
            recipe.setImg(recipeImage);
        }
        sqlAddRecipe();
        sqlAddTags();
        sqlAddProducts();
        sqlAddSteps();
        this.finish();
    }

    private void sqlAddRecipe() {
        ContentValues cv = new ContentValues();
        cv.put("id", recipe.getId());
        cv.put("name", recipe.getName());
        cv.put("image", recipe.getBitmapAsByteArray());
        cv.put("short_description", recipe.getComment());
        cv.put("mark",0);
        dbAdapter.insert("recipes", cv);
        cv.clear();
    }

    private void sqlAddProducts() {
        ContentValues cv = new ContentValues();
        for (String product : products) {
            cv.put("rec_id", recipe.getId());
            cv.put("name", product);
            cv.put("product_count", 1);
            dbAdapter.insert("products", cv);
            cv.clear();
        }
    }

    private void sqlAddTags() {
        ContentValues cv = new ContentValues();
        for (String tag : tags) {
            cv.put("rec_id", recipe.getId());
            cv.put("name", tag);
            dbAdapter.insert("tags", cv);
            cv.clear();
        }
    }

    private void sqlAddSteps() {
        ContentValues cv = new ContentValues();
        for (Step step : steps) {

            int id = recipe.getId();
            int number = step.getStepNumber();
            String description = step.getDescription();

            cv.put("rec_id", recipe.getId());
            cv.put("number_of_step", step.getStepNumber());
            cv.put("image", step.getBitmapAsByteArray());
            cv.put("description", step.getDescription());
            dbAdapter.insert("steps", cv);
            cv.clear();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Recipe.id--;
//        Intent intent  = new Intent(this, Show_recipes1.class);
//        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
