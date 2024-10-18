package com.example.flaggame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GuessCountryActivity extends AppCompatActivity {

    private ImageView flagImage;
    private HashMap<Bitmap, String> flagBitmap;
    private List<Bitmap> flagList;
    private String rightCountry;
    private TextView resultText;
    private Button submitBtn;
    private boolean isNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_country);

        flagImage = findViewById(R.id.imageView);
        submitBtn = findViewById(R.id.submitBtn);
        Spinner countriesSpinner = findViewById(R.id.countriesSpinner);
        resultText = findViewById(R.id.resultText);
        ImageView backBtn = (ImageView) findViewById(R.id.backImgView2);

        // initialise hashmap
        flagBitmap = new HashMap<>();
        // load bitmaps and store them
        flagBitmap.put(loadBitmap(R.drawable.algeria), "Algeria");
        flagBitmap.put(loadBitmap(R.drawable.australia), "Australia");
        flagBitmap.put(loadBitmap(R.drawable.belgium), "Belgium");
        flagBitmap.put(loadBitmap(R.drawable.brazil), "Brazil");
        flagBitmap.put(loadBitmap(R.drawable.canada), "Canada");
        flagBitmap.put(loadBitmap(R.drawable.colombia), "Colombia");
        flagBitmap.put(loadBitmap(R.drawable.croatia), "Croatia");
        flagBitmap.put(loadBitmap(R.drawable.denmark), "Denmark");
        flagBitmap.put(loadBitmap(R.drawable.egypt), "Egypt");
        flagBitmap.put(loadBitmap(R.drawable.finland), "Finland");
        flagBitmap.put(loadBitmap(R.drawable.france), "France");
        flagBitmap.put(loadBitmap(R.drawable.germany), "Germany");
        flagBitmap.put(loadBitmap(R.drawable.greece), "Greece");
        flagBitmap.put(loadBitmap(R.drawable.portugal), "Portugal");
        flagBitmap.put(loadBitmap(R.drawable.singapore), "Singapore");
        flagBitmap.put(loadBitmap(R.drawable.spain), "Spain");
        flagBitmap.put(loadBitmap(R.drawable.sweden), "Sweden");
        flagBitmap.put(loadBitmap(R.drawable.switzerland), "Switzerland");
        flagBitmap.put(loadBitmap(R.drawable.turkey), "Turkey");
        flagBitmap.put(loadBitmap(R.drawable.ukraine), "Ukraine");
        flagBitmap.put(loadBitmap(R.drawable.zimbabwe), "Zimbabwe");

        // list of bitmaps to randomise
        flagList = new ArrayList<>(flagBitmap.keySet());

        // show random flag when activity loads
        showRandomFlag();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countriesSpinner.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuessCountryActivity.this, NewGameActivity.class));
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNext) {
                    showRandomFlag();
                    isNext = false;  // reset to submit
                } else {
                    String chosenCountry = countriesSpinner.getSelectedItem().toString();
                    checkAnswer(chosenCountry);
                }
            }
        });

        }

        private Bitmap loadBitmap(int i) {
            // loads image as bitmap with BitmapFactory
            return BitmapFactory.decodeResource(getResources(), i);
        }

        private void showRandomFlag() {
        // chooses random bitmap from list
        Random random = new Random();
        Bitmap randomFlag = flagList.get(random.nextInt(flagList.size()));

        flagImage.setImageBitmap(randomFlag);   // sets imageview to random flag

        rightCountry = flagBitmap.get(randomFlag);

        // resets result msg
        resultText.setText("");
        submitBtn.setText("Submit");
        isNext = false;
        }

        private void checkAnswer(String chosenCountry) {

        if (chosenCountry.equals(rightCountry)) {
            resultText.setText("Correct!");
            resultText.setTextColor(Color.GREEN);
        } else {
            resultText.setText("Wrong! The right answer is: " + rightCountry);
            resultText.setTextColor(Color.RED);
        }

        submitBtn.setText("Next");
        isNext = true;
        }
    }