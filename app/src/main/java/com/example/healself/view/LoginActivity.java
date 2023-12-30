package com.example.healself.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healself.R;
import com.example.healself.controller.LoginController;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnregister;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etName;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        etEmail.setText(loginController.getEmail());
        etPassword.setText(loginController.getPassword());

        // Récupération du nom d'utilisateur depuis SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");

        // Affichage du nom d'utilisateur dans le champ de nom
        etName.setText(savedUsername);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName, password;
                boolean verifEmail = false, verifPassword = false, verifUsername = false;

                if (!etEmail.getText().toString().isEmpty())
                    verifEmail = true;
                else
                    Toast.makeText(LoginActivity.this, "Email invalid !", Toast.LENGTH_SHORT).show();

                if (!etPassword.getText().toString().isEmpty())
                    verifPassword = true;
                else
                    Toast.makeText(LoginActivity.this, "Password invalid !", Toast.LENGTH_SHORT).show();

                if (!etName.getText().toString().isEmpty())
                    verifUsername = true;
                else
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();

                if (verifEmail && verifPassword && verifUsername) {
                    userName = etEmail.getText().toString();
                    password = etPassword.getText().toString();
                    loginController.createUser(userName, password, LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        loginController = LoginController.getInstance(LoginActivity.this);
        btnLogin = findViewById(R.id.btnLogin);
        btnregister = findViewById(R.id.btnregister);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.EtName);
    }
}
