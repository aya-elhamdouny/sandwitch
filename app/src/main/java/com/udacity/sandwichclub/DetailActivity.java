package com.udacity.sandwichclub;

import android.annotation.SuppressLint;
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

import java.security.PrivateKey;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView mimageview;
    private TextView moriginlabel;
    private TextView morigin;
    private TextView malsoknownlabel ;
    private TextView malsoknown;
    private TextView mingredients;
    private TextView mdescription;



    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
         mimageview = (ImageView) findViewById(R.id.image_iv);
         morigin = (TextView)  findViewById(R.id.origin_tv);
         malsoknown = (TextView) findViewById(R.id.also_known_tv);
         mingredients = (TextView)findViewById(R.id.ingredients_tv);
         mdescription = (TextView)findViewById(R.id.description_tv);
        // malsoknownlabel;
        // moriginlabel;



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
        if (sandwich.getPlaceOfOrigin().isEmpty())
        {
            moriginlabel.setVisibility(View.INVISIBLE);
            morigin.setVisibility(View.INVISIBLE);
        }
        else
            {
            morigin.setText(sandwich.getPlaceOfOrigin());
            }

        if (sandwich.getAlsoKnownAs().isEmpty())
        {
            malsoknownlabel.setVisibility(View.INVISIBLE);
            malsoknown.setVisibility(View.INVISIBLE);
        } else
         {
            List<String> aka = sandwich.getAlsoKnownAs();
            String aka_str = TextUtils.join(", ", aka);
            malsoknown.setText(aka_str);
        }

             mdescription.setText(sandwich.getDescription());

             List<String> ing = sandwich.getIngredients();
             String ing_str = TextUtils.join(", ", ing);
             mingredients.setText(ing_str);

    }
}
