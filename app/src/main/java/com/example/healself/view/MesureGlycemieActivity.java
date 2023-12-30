package com.example.healself.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.healself.R;

public class MesureGlycemieActivity extends AppCompatActivity {
    private EditText etAge;
    private EditText mesureEditText;
    private RadioButton fastingYesRadioButton;
    private RadioButton fastingNoRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesure_glycemie);

        etAge = findViewById(R.id.ageEditText);
        mesureEditText = findViewById(R.id.mesureEditText);
        fastingYesRadioButton = findViewById(R.id.maleRadioButton);
        fastingNoRadioButton = findViewById(R.id.femaleRadioButton);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGlycemia();
            }

            private void calculateGlycemia() {
                String age = etAge.getText().toString();
                String mesure = mesureEditText.getText().toString();
                boolean isFasting = fastingYesRadioButton.isChecked();

                if (age.isEmpty() || mesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double mesureValue = Double.parseDouble(mesure);

                // Pass the data to the ResultGlycemieActivity
                Intent intent = new Intent(MesureGlycemieActivity.this, ResultGlycemieActivity.class);
                intent.putExtra("AGE", age);
                intent.putExtra("MESURE_VALUE", mesureValue);
                intent.putExtra("IS_FASTING", isFasting);
                startActivity(intent);
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