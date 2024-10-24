package com.example.flaggame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GuessHintsActivity extends AppCompatActivity {

    // xml initialising
    private ImageView flagImage;
    private TextView countryDashes;
    private TextView hintResultTxt;
    private Button submitButton;
    private EditText guessInput;

    // game data initialising
    private HashMap<Bitmap, String> flagBitmap;  // links flag bitmaps with country names
    private List<Bitmap> flagList;   // lists all flag bitmaps for randomising
    private String rightCountry;   // stores right country for flag
    private char[] dashes;  // array for dashes
    private boolean isNext;  // for when to load new flag or submit
    private int wrongGuess;
    private final int maxWrongGuess = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints);

        // xml elements
        flagImage = findViewById(R.id.imageView2);
        countryDashes = findViewById(R.id.countryDashes);
        guessInput = findViewById(R.id.guessInput);
        hintResultTxt = findViewById(R.id.hintResultTxt);
        submitButton = findViewById(R.id.submitButton);
        ImageView backBtn = (ImageView) findViewById(R.id.backImgView4);

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

        showRandomFlag();   // show random when activity starts

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuessHintsActivity.this, NewGameActivity.class));
            }
        });
        // submit/next button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if its the next flag, reset and show new flag
                if (isNext) {
                    showRandomFlag();
                    hintResultTxt.setText("");  // reset result text
                    isNext = false;
                } else {
                    String userInput = guessInput.getText().toString().trim(); // .trim to remove unneeded space
                    if (!userInput.isEmpty()) {
                        char guessedLetter = userInput.charAt(0);
                        checkDashes(guessedLetter);  // check guess
                    }
                }
            }
        });


    }

    // load bitmap of flag
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

        dashes = new char[rightCountry.length()];

        int i = 0;

        // while loop for creating dashes
        while (i < dashes.length) {
            if (rightCountry.charAt(i) == ' ') {
                dashes[i] = ' ';   // leave the space as is
            } else {
                dashes[i] = '-';
            }
            i++;
        }
        updateDash();   // update textview for dashes
    }

    // updates dashes
    private void updateDash() {
        countryDashes.setText(new String(dashes));
    }

    // checks if guessed letter is right, update dashes, change game state
    private void checkDashes(char guessedChar) {
        guessedChar = Character.toUpperCase(guessedChar);
        boolean found = false;

        // loops through country names to check if guessed char is in name
        int i = 0;
        while (i < rightCountry.length()) {
            if (Character.toUpperCase(rightCountry.charAt(i)) == guessedChar) {
                dashes[i] = rightCountry.charAt(i);  // replace dash with right letter
                found = true;
            }
            i++;
        }

        if (found) {
            updateDash();    // update if character is found

            // if all letters are guessed right, change submit btn and result text
            if (new String(dashes).equals(rightCountry)) {
                submitButton.setText("Next");
                isNext = true;  // will load new flag on next click
                hintResultTxt.setText("Correct!");
                hintResultTxt.setTextColor(Color.GREEN);
            }
        } else {
            wrongGuess = wrongGuess + 1;

            // if max wrong guesses are hit, change result txt, dashes and submit btn
            if (wrongGuess >= maxWrongGuess) {
                hintResultTxt.setText("Wrong! The right country was: " + rightCountry);
                hintResultTxt.setTextColor(Color.RED);
                countryDashes.setText(rightCountry);
                submitButton.setText("Next");
                isNext = true;
            }
        }

        // clear guess input each guess
        guessInput.setText("");
    }
}
