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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity {

    private ImageView flag1, flag2, flag3;
    private EditText flagOneName, flagTwoName, flagThreeName;
    private Button btnSubmit;
    private TextView scoreTextView, feedbackTextView;

    private HashMap<Bitmap, String> flagBitmap;
    private List<Bitmap> flagList;
    private String[] rightCountryNames = new String[3];
    private int tries = 0;
    private int maxTries = 3;
    private int score = 0;
    private boolean isNextRound = false;   // tracks if we're waiting for next round

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        flag1 = findViewById(R.id.flag1);
        flag2 = findViewById(R.id.flag2);
        flag3 = findViewById(R.id.flag3);
        flagOneName = findViewById(R.id.flagOneName);
        flagTwoName = findViewById(R.id.flagTwoName);
        flagThreeName = findViewById(R.id.flagThreeName);
        btnSubmit = findViewById(R.id.btnSubmit);
        scoreTextView = findViewById(R.id.scoreTextView);
        feedbackTextView = findViewById(R.id.feedbackTextView);
        ImageView backBtn = (ImageView) findViewById(R.id.backImgView5);

        // initialises score
        scoreUpdate();


        flagBitmap = new HashMap<>();
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

        flagList = new ArrayList<>(flagBitmap.keySet());


        showRandomFlags();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdvancedLevelActivity.this, NewGameActivity.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers();
            }
        });
    }

    private Bitmap loadBitmap(int i) {

        return BitmapFactory.decodeResource(getResources(), i);
    }

    private void showRandomFlags() {
        Random random = new Random();
        Bitmap randomFlag1 = flagList.get(random.nextInt(flagList.size()));
        Bitmap randomFlag2 = flagList.get(random.nextInt(flagList.size()));

        // ensures all flags are unique
        while (randomFlag2.equals(randomFlag1)) {
            randomFlag2 = flagList.get(random.nextInt(flagList.size()));
        }
        Bitmap randomFlag3 = flagList.get(random.nextInt(flagList.size()));
        while (randomFlag3.equals(randomFlag1) || randomFlag3.equals(randomFlag2)) {
            randomFlag3 = flagList.get(random.nextInt(flagList.size()));
        }

        flag1.setImageBitmap(randomFlag1);
        flag2.setImageBitmap(randomFlag2);
        flag3.setImageBitmap(randomFlag3);

        // Store correct country names
        rightCountryNames[0] = flagBitmap.get(randomFlag1);
        rightCountryNames[1] = flagBitmap.get(randomFlag2);
        rightCountryNames[2] = flagBitmap.get(randomFlag3);
    }

    private void checkAnswers() {

        if (isNextRound) {
            resetNextRound();
            return;
        }

        if (flagOneName.getText().toString().trim().isEmpty() ||
            flagTwoName.getText().toString().trim().isEmpty() ||
            flagThreeName.getText().toString().trim().isEmpty()) {
            feedbackTextView.setText("Please enter answers for all flags.");
            feedbackTextView.setTextColor(Color.RED);
            return;
        }

        boolean allRight = true;
        int rightAnswers = 0;

        // Check the first flag
        if (flagOneName.getText().toString().trim().equalsIgnoreCase(rightCountryNames[0])) {
            flagOneName.setEnabled(false);
            flagOneName.setBackgroundColor(Color.GREEN);
            rightAnswers = rightAnswers + 1;
        } else {
            flagOneName.setBackgroundColor(Color.RED);
            allRight = false;
        }

        // Check the second flag
        if (flagTwoName.getText().toString().trim().equalsIgnoreCase(rightCountryNames[1])) {
            flagTwoName.setEnabled(false);
            flagTwoName.setBackgroundColor(Color.GREEN);
            rightAnswers = rightAnswers + 1;
        } else {
            flagTwoName.setBackgroundColor(Color.RED);
            allRight = false;
        }

        // Check the third flag
        if (flagThreeName.getText().toString().trim().equalsIgnoreCase(rightCountryNames[2])) {
            flagThreeName.setEnabled(false);
            flagThreeName.setBackgroundColor(Color.GREEN);
            rightAnswers = rightAnswers + 1;
        } else {
            flagThreeName.setBackgroundColor(Color.RED);
            allRight = false;
        }

        score += rightAnswers;
        scoreUpdate();


        if (rightAnswers == 3) {
            feedbackTextView.setText("Correct!");
            feedbackTextView.setTextColor(Color.GREEN);
        } else if (rightAnswers > 0) {
            feedbackTextView.setText("You got " + rightAnswers + " correct!");
            feedbackTextView.setTextColor(Color.YELLOW);
        } else {
            feedbackTextView.setText("Wrong! All answers are incorrect.");
            feedbackTextView.setTextColor(Color.RED);
        }

        // Set the button text to "Next" for the next round
        btnSubmit.setText("Next");
        isNextRound = true;
    }

    private void resetNextRound() {

        flagOneName.setEnabled(true);
        flagOneName.setText("");
        flagOneName.setBackgroundColor(Color.TRANSPARENT);  // resets bg color of textbox

        flagTwoName.setEnabled(true);
        flagTwoName.setText("");
        flagTwoName.setBackgroundColor(Color.TRANSPARENT);

        flagThreeName.setEnabled(true);
        flagThreeName.setText("");
        flagThreeName.setBackgroundColor(Color.TRANSPARENT);

        feedbackTextView.setText("");
        tries = 0;

        showRandomFlags();
        btnSubmit.setText("Submit");
        isNextRound = false;
    }

    private void scoreUpdate() {
        scoreTextView.setText("Score: " + score);
    }
}