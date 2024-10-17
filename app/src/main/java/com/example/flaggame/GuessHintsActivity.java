package com.example.flaggame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GuessHintsActivity extends AppCompatActivity {

    private ImageView flagImage;
    private TextView countryDashes;
    private Button submitButton;
    private EditText guessInput;

    private HashMap<Bitmap, String> flagBitmap;
    private List<Bitmap> flagList;
    private String rightCountry;
    private char[] dashes;
    private boolean isNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);

        flagImage = findViewById(R.id.imageView2);
        countryDashes = findViewById(R.id.countryDashes);
        guessInput = findViewById(R.id.guessInput);
        submitButton = findViewById(R.id.submitButton);

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

        // list of bitmaps for randomisation
        flagList = new ArrayList<>(flagBitmap.keySet());

        showRandomFlag();

        // submit/next button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNext) {
                    showRandomFlag();
                    isNext = false;
                } else {
                    String userInput = guessInput.getText().toString().trim(); // .trim to remove unneeded space
                    if (!userInput.isEmpty()) {
                        char guessedLetter = userInput.charAt(0);
                        updateDash(guessedLetter);  // check guess
                    }
                }
            }
        });


        }

        private Bitmap loadBitmap(int i) {
            return BitmapFactory.decodeResource(getResources(), i);
        }

        // random flag and dashes with it
        private void showRandomFlag() {

            Random random = new Random();
            Bitmap randomFlag = flagList.get(random.nextInt(flagList.size()));

            // set image to random flag
            flagImage.setImageBitmap(randomFlag);

            rightCountry = flagBitmap.get(randomFlag);
            createDashes();  // create dashes based on right country

            guessInput.setText("");
            submitButton.setText("Submit");
            isNext = false;
        }

        private void createDashes() {

        }

        private void updateDash() {

        }
    }