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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gameBtn = (Button) findViewById(R.id.gameBtn);
        Button aboutBtn = (Button) findViewById(R.id.aboutBtn);

        // onClickListener for game button
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewGameActivity.class));  // starts new activity
            }
        });

        // onClickListener for about button
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AboutFragment());
            }
        });
        }

        // loads fragment into the layout created
        private void loadFragment(Fragment fragment) {

            FragmentManager fm = getSupportFragmentManager();
            // begin transaction and replace fragment
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // replace the layout with new fragment
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit(); // saves changes
        }

    }