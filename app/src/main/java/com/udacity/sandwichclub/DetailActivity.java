package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();

        int position = DEFAULT_POSITION;

        if (intent == null) {
            closeOnError();
        } else {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        }

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
        // emblem_unavailable taken from
        // https://upload.wikimedia.org/wikipedia/commons/4/44/Antu_emblem-unavailable.svg
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.emblem_unavailable)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView descriptionLabelTv = findViewById(R.id.description_label_tv);
        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        TextView alsoKnownAsLabelTv = findViewById(R.id.also_known_label_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        TextView originLabelTv = findViewById(R.id.origin_label_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView ingredientsLabelTv = findViewById(R.id.ingredients_label_tv);
        if (sandwich.getDescription() != null && !sandwich.getDescription().isEmpty()) {
            descriptionTv.setText(sandwich.getDescription());
            descriptionLabelTv.setVisibility(View.VISIBLE);
            descriptionTv.setVisibility(View.VISIBLE);
        } else {
            descriptionLabelTv.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);
        }

        if (sandwich.getPlaceOfOrigin() != null && !sandwich.getPlaceOfOrigin().isEmpty()) {
            originLabelTv.setEnabled(true);
            originTv.setEnabled(true);
            originTv.setText(sandwich.getPlaceOfOrigin());
        } else {
            originLabelTv.setVisibility(View.GONE);
            originTv.setVisibility(View.GONE);
        }

        if (sandwich.getIngredients() != null && !sandwich.getIngredients().isEmpty()) {
            ingredientsLabelTv.setVisibility(View.VISIBLE);
            ingredientsTv.setVisibility(View.VISIBLE);
            ingredientsTv.setText(TextUtils.join("\n", sandwich.getIngredients()));
        } else {
            ingredientsLabelTv.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);
        }

        if (sandwich.getAlsoKnownAs() != null && !sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnownAsLabelTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setText(TextUtils.join("\n", sandwich.getAlsoKnownAs()));
        } else {
            alsoKnownAsLabelTv.setVisibility(View.GONE);
            alsoKnownAsTv.setVisibility(View.GONE);
        }
    }
}
