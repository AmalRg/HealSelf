package com.example.healself.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healself.R;

public class ResultImcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_imc);


        LinearLayout backHomeLayout = findViewById(R.id.backHome);
        backHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultImcActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Récupération du BMI depuis l'intent
        double bmi = getIntent().getDoubleExtra("BMI_RESULT", 0.0); // 0.0 est la valeur par défaut si la clé n'est pas trouvée

        // Récupération des TextViews dans ResultImcActivity
        TextView bmiScoreTextView = findViewById(R.id.bmiScore);
        TextView bmiStatusTextView = findViewById(R.id.bmiStatus);
        TextView notesTextView = findViewById(R.id.tvNotes);

        // Affichage des résultats
        if (bmiScoreTextView != null && bmiStatusTextView != null && notesTextView != null) {
            bmiScoreTextView.setText(String.format("%.1f", bmi));

            String bmiStatus;
            String notes;

            if (bmi < 18.5) {
                bmiStatus = "Underweight";
                notes = "You might want to consider gaining some weight. Ensure it's through a healthy diet.";
            } else if (bmi >= 18.5 && bmi < 24.9) {
                bmiStatus = "Normal Weight";
                notes = "Maintain a healthy lifestyle with balanced nutrition and regular exercise.";
            } else if (bmi >= 25 && bmi < 29.9) {
                bmiStatus = "Overweight";
                notes = "Consider focusing on a balanced diet and increasing physical activity to reach a healthier weight.";
            } else {
                bmiStatus = "Obesity";
                notes = "Seek advice from a healthcare professional to create a tailored plan for weight management.";
            }

            bmiStatusTextView.setText(bmiStatus);
            notesTextView.setText(notes);
        } else {
            Log.e("TAG", "One or more TextViews are null");
        }


        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}