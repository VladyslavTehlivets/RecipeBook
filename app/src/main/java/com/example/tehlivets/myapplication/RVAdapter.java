package com.example.tehlivets.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> l_recipes;

    private Cursor cursor;
    private DbAdapter dbAdapter;

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView recipeName;
        TextView recipeDescription;
        ImageButton recipeDelete;
        ImageView recipePhoto;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
            recipeName = (TextView) itemView.findViewById(R.id.recipe_name);
            recipeDescription = (TextView) itemView.findViewById(R.id.recipe_comment);
            recipePhoto = (ImageView) itemView.findViewById(R.id.recipePhoto);
            recipeDelete = (ImageButton) itemView.findViewById(R.id.recipe_delete);
        }
    }

    public RVAdapter(DbAdapter dbAdapter, Context context, List<Recipe> l_recipes) {
        this.l_recipes = l_recipes;
        this.context = context;
        this.dbAdapter = dbAdapter;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_card, viewGroup,false);
        RecipeViewHolder pvh = new RecipeViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder recipeViewHolder, final int position) {

        final String recipeName = l_recipes.get(position).getName();
        final String recipeComment = l_recipes.get(position).getComment();

        //recipeName
        recipeViewHolder.recipeName.setText(recipeName);
        //recipePhoto
        if(l_recipes.get(position).isBitmap()){
            Bitmap recPhoto = l_recipes.get(position).getBitmap();
            recipeViewHolder.recipePhoto.setImageBitmap(recPhoto);
        }else {
            int recipePhoto = l_recipes.get(position).getPhotoId();
            recipeViewHolder.recipePhoto.setImageResource(recipePhoto);
        }
        //recipeDescription
        recipeViewHolder.recipeDescription.setText(recipeComment);

        recipeViewHolder.recipePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View photo) {
                Toast.makeText(context, "Recipe name " + recipeName, Toast.LENGTH_SHORT).show();
                int id = l_recipes.get(position).getId();
                Intent recipeView = new Intent(context, RecipeView.class);
                recipeView.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle b = new Bundle();
                b.putInt("recipeId",l_recipes.get(position).getId());
                recipeView.putExtras(b);
                context.startActivity(recipeView);
            }
        });
        recipeViewHolder.recipeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View delete) {
                Toast.makeText(context, "Delete " + recipeName + "?", Toast.LENGTH_SHORT).show();
                deleteRecipe(recipeName,position);
            }
        });
    }

    private void deleteRecipe(String recipeName, int position) {
        cursor = dbAdapter.rawQuery("select id from recipes where name = '" + recipeName + "'");
        int id = cursor.getColumnIndex("id");
        cursor.moveToFirst();
        int recipe_id = cursor.getInt(id);

        dbAdapter.execSQL("delete from products where rec_id = " + recipe_id + "");
        dbAdapter.execSQL("delete from steps where rec_id = " + recipe_id + "");
        dbAdapter.execSQL("delete from tags where rec_id = " + recipe_id + "");
        dbAdapter.execSQL("delete from recipes where name = '" + recipeName + "'");
        l_recipes.remove(position);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return l_recipes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
