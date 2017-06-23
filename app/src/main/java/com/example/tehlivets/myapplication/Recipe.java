package com.example.tehlivets.myapplication;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Recipe {

    public static int id = 0;
    private int Id;
    private String name = "";
    private String desctription = "";
    private Bitmap img = null;
    private int photoId = R.drawable.recipe_photo;
    private int deletePhotoId = R.id.recipe_delete;

    private List<String> recipeTags = new ArrayList<>();
    private List<String> recipeProducts = new ArrayList<>();
    private List<Step> recipeSteps = new ArrayList<>();

    public Recipe(String name, String description, Bitmap bitmap,int id) {
        this.name = name;
        this.desctription = description;
        this.img = bitmap;
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<String> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(List<String> recipeTags) {
        this.recipeTags = recipeTags;
    }

    public List<String> getRecipeProducts() {
        return recipeProducts;
    }

    public void setRecipeProducts(List<String> recipeProducts) {
        this.recipeProducts = recipeProducts;
    }

    public Recipe() {
        Recipe.id++;
        this.Id = Recipe.id;
    }

    public void setRecipeSteps(List<Step> recipe_steps) {
        this.recipeSteps = recipe_steps;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isBitmap() {
        return img == null ? false : true;
    }

    public void setDesctription(String comment) {
        this.desctription = comment;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void add_step(Step step) {
        recipeSteps.add(step);
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return desctription;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getDeletePhotoId() {
        return deletePhotoId;
    }

    public List<Step> getRecipeSteps() {
        return recipeSteps;
    }

    public byte[] getBitmapAsByteArray() {
        if (img != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            return outputStream.toByteArray();
        } else return null;
    }

    public Bitmap getBitmap() {
        return img;
    }
}
