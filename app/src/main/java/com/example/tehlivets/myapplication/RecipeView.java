package com.example.tehlivets.myapplication;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeView extends FragmentActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRecipeView);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void closeRecipeView(View view) {
        this.finish();
    }

    public static class RecipeCooking extends Fragment {
        Cursor cursor;
        DbAdapter dbAdapter;
        private StepRVAdapter adapter;
        private int Id;

        private ArrayList<Step> l_steps = new ArrayList<>();
        private LinearLayoutManager llm_steps;
        private RecyclerView steps;

        private static final String ARG_SECTION_NUMBER = "section_number1";

        public RecipeCooking() {

        }

        public static RecipeCooking newInstance(int sectionNumber) {
            RecipeCooking fragment = new RecipeCooking();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.recipe_cooking, container, false);

            dbAdapter = new DbAdapter(getActivity().getApplicationContext(), getString(R.string.database)).open();

            Bundle b = getActivity().getIntent().getExtras();
            this.Id = b.getInt("recipeId");

            steps = (RecyclerView) rootView.findViewById(R.id.stepsView);
            setSteps();

            llm_steps = new LinearLayoutManager(getActivity());
            steps.setHasFixedSize(true);
            steps.setLayoutManager(llm_steps);

            adapter = new StepRVAdapter(getActivity(), l_steps);
            steps.setAdapter(adapter);
            return rootView;
        }

        private void setSteps() {
            cursor = dbAdapter.rawQuery("SELECT * FROM steps WHERE rec_id = '" + this.Id + "'");
            int size = cursor.getCount();
            int number_of_step = cursor.getColumnIndex("number_of_step");
            int description = cursor.getColumnIndex("description");
            int image = cursor.getColumnIndex("image");


            byte[] photo = null;
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                photo = cursor.getBlob(image);
                if (photo != null) {
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                    l_steps.add(new Step(cursor.getInt(number_of_step), cursor.getString(description), decodedByte));
                }else {
                    l_steps.add(new Step(cursor.getInt(number_of_step), cursor.getString(description), null));
                }
            }
        }
    }

    public static class RecipeOverView extends Fragment {
        Cursor cursor;
        DbAdapter dbAdapter;

        private TextView recipeTags;
        private TextView recipeProducts;

        private ImageView recipePhoto;
        private int Id;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public RecipeOverView() {

        }

        public static RecipeOverView newInstance(int sectionNumber) {
            RecipeOverView fragment = new RecipeOverView();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.recipe_overview, container, false);

            recipePhoto = (ImageView) rootView.findViewById(R.id.recipePhotoView);
            recipeTags = (TextView) rootView.findViewById(R.id.tagsView);
            recipeProducts = (TextView) rootView.findViewById(R.id.productsView);
            dbAdapter = new DbAdapter(getActivity().getApplicationContext(), getString(R.string.database)).open();
            Bundle b = getActivity().getIntent().getExtras();
            this.Id = b.getInt("recipeId");
            setImage();
            setProducts();
            setTags();
            return rootView;
        }

        private void setTags() {
            cursor = dbAdapter.rawQuery("SELECT name FROM tags WHERE rec_id = '" + this.Id + "'");
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                recipeTags.setText(recipeTags.getText() + cursor.getString(0) + "; ");
            }
        }

        private void setProducts() {
            cursor = dbAdapter.rawQuery("SELECT name FROM products WHERE rec_id = '" + this.Id + "'");
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                recipeProducts.setText(recipeProducts.getText() + "\n * " + cursor.getString(0));
            }
        }

        private void setImage() {
            cursor = dbAdapter.rawQuery("SELECT image FROM recipes WHERE id = '" + this.Id + "'");
            cursor.moveToFirst();
            byte[] photo = cursor.getBlob(0);
            if (photo != null) {
                Bitmap decodedByte = BitmapFactory.decodeByteArray(photo, 0, photo.length);
                recipePhoto.setImageBitmap(decodedByte);
            }
        }

    }
}
