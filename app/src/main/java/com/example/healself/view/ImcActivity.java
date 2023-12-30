package com.example.healself.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healself.R;

public class ImcActivity extends AppCompatActivity {
    private EditText etAge;
    private EditText etHeight;
    private EditText etWeight;
    private RadioButton male;
    private RadioButton femele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        etAge = findViewById(R.id.ageEditText);
        etHeight = findViewById(R.id.heightEditText);
        etWeight = findViewById(R.id.weightEditText);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateImc();
            }

            private void calculateImc() {
                String age = etAge.getText().toString();
                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                if (age.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double heightInMeters = Double.parseDouble(height) / 100;
                double weightInKg = Double.parseDouble(weight);

                double bmi = weightInKg / (heightInMeters * heightInMeters);

                displayResult(bmi);

                Intent intent = new Intent(ImcActivity.this, ResultImcActivity.class);
                intent.putExtra("BMI_RESULT", bmi);
                startActivity(intent);
            }

            private void displayResult(double bmi) {
                TextView bmiScoreTextView = findViewById(R.id.bmiScore);
                TextView bmiStatusTextView = findViewById(R.id.bmiStatus);
                TextView notesTextView = findViewById(R.id.tvNotes);

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
            }

        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}