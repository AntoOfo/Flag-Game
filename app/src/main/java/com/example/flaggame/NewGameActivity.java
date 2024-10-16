package com.example.flaggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Button guessCountryBtn = (Button) findViewById(R.id.guessCountryBtn);
        Button guessFlagBtn = (Button) findViewById(R.id.guessFlagBtn);
        Button hintsBtn = (Button) findViewById(R.id.hintsBtn);
        Button advancedBtn = (Button) findViewById(R.id.advancedBtn);

        guessCountryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewGameActivity.this, GuessCountryActivity.class));
            }
        });
        }
    }