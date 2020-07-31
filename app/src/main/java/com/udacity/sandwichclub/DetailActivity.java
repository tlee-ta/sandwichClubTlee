package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    // Initialize all UI components
    ImageView ingredientsIv;
    TextView alsoKnownAsTv;
    TextView tAlsoKnownAsTv;
    TextView ingredientsTv;
    TextView tIngredientsTv;
    TextView placeOfOriginTv;
    TextView tPlaceOfOriginTv;
    TextView descriptionTv;
    TextView tDescriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        // Set all UI components
        alsoKnownAsTv = findViewById(R.id.also_known_tv);
        tAlsoKnownAsTv = findViewById(R.id.title_also_known);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        tIngredientsTv = findViewById(R.id.title_ingredients);
        placeOfOriginTv = findViewById(R.id.origin_tv);
        tPlaceOfOriginTv = findViewById(R.id.title_origin);
        descriptionTv = findViewById(R.id.description_tv);
        tDescriptionTv = findViewById(R.id.title_description);

        // Remove the brackets from the string list for "Also Known As"
        String strAKA = sandwich.getAlsoKnownAs().toString();
        strAKA = strAKA.substring(1, strAKA.length()-1);

        // Check if the "Also Known As" field is empty
        // Remove from the UI when it is empty, show the UI and populate the component if it isn't
        if (strAKA == null || strAKA.isEmpty())
        {
            tAlsoKnownAsTv.setVisibility(View.GONE);
            alsoKnownAsTv.setVisibility(View.GONE);
        }
        else
        {
            tAlsoKnownAsTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setText(strAKA);
        }

        // Remove the brackets from the string list for "Ingredients"
        String strIngre = sandwich.getIngredients().toString();
        strIngre = strIngre.substring(1, strIngre.length()-1);

        // Check if the "Ingredients" field is empty
        // Remove from the UI when it is empty, show the UI and populate the component if it isn't
        if (strIngre == null || strIngre.isEmpty())
        {
            tIngredientsTv.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);
        }
        else
        {
            tIngredientsTv.setVisibility(View.VISIBLE);
            ingredientsTv.setVisibility(View.VISIBLE);
            ingredientsTv.setText(strIngre);
        }

        // Check if the "Place of Origin" field is empty
        // Remove from the UI when it is empty, show the UI and populate the component if it isn't
        if (sandwich.getPlaceOfOrigin() == null || sandwich.getPlaceOfOrigin().isEmpty())
        {
            tPlaceOfOriginTv.setVisibility(View.GONE);
            placeOfOriginTv.setVisibility(View.GONE);
        }
        else
        {
            tPlaceOfOriginTv.setVisibility(View.VISIBLE);
            placeOfOriginTv.setVisibility(View.VISIBLE);
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        }

        // Check if the "Description" field is empty
        // Remove from the UI when it is empty, show the UI and populate the component if it isn't
        if (sandwich.getDescription() == null || sandwich.getDescription().isEmpty())
        {
            tDescriptionTv.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);
        }
        else
        {
            tDescriptionTv.setVisibility(View.VISIBLE);
            descriptionTv.setVisibility(View.VISIBLE);
            descriptionTv.setText(sandwich.getDescription());
        }
    }
}
