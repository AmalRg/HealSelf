package com.example.healself.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healself.R;

public class ResultGlycemieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_glycemie);

        Intent intent = getIntent();
        if (intent != null) {
            String ageString = intent.getStringExtra("AGE");
            int age = Integer.parseInt(ageString);
            double mesureValue = intent.getDoubleExtra("MESURE_VALUE", 0.0);
            boolean isFasting = intent.getBooleanExtra("IS_FASTING", true);

            TextView bloodStatusTextView = findViewById(R.id.bloodStatus);
            TextView notesTextView = findViewById(R.id.tvNotes);

            String bloodStatus;
            String notes;

            if(isFasting) {    // il est a jeun
                if (age >= 13) {
                    if (mesureValue < 5.0) {
                        bloodStatus = "Hypoglycemia";
                        notes = "You might need to consume carbohydrates to increase your blood sugar. Consult a healthcare professional if necessary.";
                    }else if (mesureValue >= 5.0 && mesureValue <= 7.2) {
                        bloodStatus = "Normal";
                        notes = "Maintain a healthy diet and regularly monitor your blood sugar to stay within the normal range.";
                    }else {
                    bloodStatus = "Hyperglycemia";
                    notes = "Avoid sugary foods, exercise regularly, and consult a healthcare professional to manage your blood sugar.";
                }} else if (age >= 6 && age <= 12) {
                    if (mesureValue < 5.0) {
                        bloodStatus = "Hypoglycemia";
                        notes = "You might need to consume carbohydrates to increase your blood sugar. Consult a healthcare professional if necessary.";
                    } else if (mesureValue >= 5.0 && mesureValue <= 10.0) {
                        bloodStatus = "Normal";
                        notes = "Maintain a healthy diet and regularly monitor your blood sugar to stay within the normal range.";
                    } else
                        bloodStatus = "Hypoglycemia";
                    notes = "You might need to consume carbohydrates to increase your blood sugar. Consult a healthcare professional if necessary.";
                } else if (mesureValue < 5.50) {
                    bloodStatus = "Hypoglycemia";
                    notes = "You might need to consume carbohydrates to increase your blood sugar. Consult a healthcare professional if necessary.";
                } else if (mesureValue >= 5.5 && mesureValue <= 10.0) {
                    bloodStatus = "Normal";
                    notes = "Maintain a healthy diet and regularly monitor your blood sugar to stay within the normal range.";
                } else {
                    bloodStatus = "Hypoglycemia";
                    notes = "You might need to consume carbohydrates to increase your blood sugar. Consult a healthcare professional if necessary.";
                }
            }
            else
                if(mesureValue <= 10.5) {
                    bloodStatus = "Normal";
                    notes = "Maintain a healthy diet and regularly monitor your blood sugar to stay within the normal range.";
                }else {
                    bloodStatus = "Hypoglycemia";
                    notes = "You might need to consume carbohydrates to increase your blood sugar. Consult a healthcare professional if necessary.";
                  }

            bloodStatusTextView.setText(bloodStatus);
            notesTextView.setText(notes);
        }

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LinearLayout backHomeLayout = findViewById(R.id.backHome);
        backHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultGlycemieActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}