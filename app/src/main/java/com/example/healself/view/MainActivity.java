package com.example.healself.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healself.R;

public class MainActivity extends AppCompatActivity {

    private TextView textViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewUsername = findViewById(R.id.username);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");

        textViewUsername.setText(savedUsername);


        LinearLayout imcLayout = findViewById(R.id.calculIMC);
        imcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImcActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout mesureGlycemieLayout = findViewById(R.id.mesureGlycemie);
        mesureGlycemieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MesureGlycemieActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout exercicesLayout = findViewById(R.id.Exercices);
        exercicesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExerciceActivity.class);
                startActivity(intent);
            }
        });
    }
}