package com.example.flaggame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class GuessFlagActivity extends AppCompatActivity {

    private ImageView flagOne, flagTwo, flagThree;
    private TextView countryName;
    private TextView resultTxt;
    private Button nextBtn;

    private HashMap<Bitmap, String> flagBitmap;
    private List<Bitmap> flagList;
    private String rightCountry;
    private Bitmap rightFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_flag);

        flagOne = findViewById(R.id.flagOne);
        flagTwo = findViewById(R.id.flagTwo);
        flagThree = findViewById(R.id.flagThree);
        countryName = findViewById(R.id.countryName);
        resultTxt = findViewById(R.id.resultTxt);
        nextBtn = findViewById(R.id.nextbtn);

        nextBtn.setVisibility(TextView.GONE);  // hide the button initially

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

        showRandomFlags();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showRandomFlags();
                nextBtn.setVisibility(TextView.GONE);
                resultTxt.setText("");
            }
        });
        }
    private Bitmap loadBitmap(int i) {
        return BitmapFactory.decodeResource(getResources(), i);
    }

    private void showRandomFlags() {
        Random random = new Random();
        // chooses 3 random flags
        Bitmap randomFlag1 = flagList.get(random.nextInt(flagList.size()));
        Bitmap randomFlag2 = flagList.get(random.nextInt(flagList.size()));

        while (randomFlag2.equals(randomFlag1)) {
            randomFlag2 = flagList.get(random.nextInt(flagList.size()));
        }
        // get third flag and ensures its unique
        Bitmap randomFlag3 = flagList.get(random.nextInt(flagList.size()));

        while (randomFlag3.equals(randomFlag1) || randomFlag3.equals(randomFlag2)) {
            randomFlag3 = flagList.get(random.nextInt(flagList.size()));
        }

        flagOne.setImageBitmap(randomFlag1);
        flagTwo.setImageBitmap(randomFlag2);
        flagThree.setImageBitmap(randomFlag3);

        Bitmap[] flags = {randomFlag1, randomFlag2, randomFlag3};
        rightFlag = flags[random.nextInt(3)];
        rightCountry = flagBitmap.get(rightFlag);

        countryName.setText(rightCountry);

        final Bitmap finalRandomFlag1 = randomFlag1;
        final Bitmap finalRandomFlag2 = randomFlag2;
        final Bitmap finalRandomFlag3 = randomFlag3;

        flagOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagChoiceCheck(finalRandomFlag1);
            }
        });

        flagTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagChoiceCheck(finalRandomFlag2);
            }
        });

        flagThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagChoiceCheck(finalRandomFlag3);
            }
        });

    }

    private void flagChoiceCheck(Bitmap chosenFlag) {

        if (chosenFlag == rightFlag) {
            resultTxt.setText("Correct!");
            resultTxt.setTextColor(Color.GREEN);
        } else {
            resultTxt.setText("Wrong! ");
            resultTxt.setTextColor(Color.RED);
        }

        nextBtn.setVisibility(TextView.VISIBLE);  // make button visible again

        disableFlagSelection();
    }

    // turns off onCLick for flags
    private void disableFlagSelection() {
        flagOne.setOnClickListener(null);
        flagTwo.setOnClickListener(null);
        flagThree.setOnClickListener(null);
    }
    }