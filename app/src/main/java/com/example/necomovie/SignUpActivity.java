package com.example.necomovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    private ConstraintLayout parentLayout;
    EditText usernameEdt;
    EditText emailEdt;
    EditText passwordEdt;
    ImageButton showingPasswordBtn;
    EditText confirmPasswordEdt;
    ImageButton showingConfirmPasswordBtn;
    CheckBox checkBox;
    Button signUpBtn;
    TextView signInTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        parentLayout = findViewById(R.id.parentLayout);
        usernameEdt =  findViewById(R.id.usernameEdt);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        showingPasswordBtn = (ImageButton) findViewById(R.id.showingPasswordBtn);
        confirmPasswordEdt = (EditText) findViewById(R.id.confirmPasswordEdt);
        showingConfirmPasswordBtn = (ImageButton) findViewById(R.id.showingConfirmPasswordBtn);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signInTv = (TextView) findViewById(R.id.signInTv);

        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
            }
        });
        showingPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showingPasswordBtn.setSelected(!showingPasswordBtn.isSelected());
                if (showingPasswordBtn.isSelected()) {
                    showingPasswordBtn.setImageResource(R.drawable.hide_password_icon);
                    passwordEdt.setTransformationMethod(null);
                    passwordEdt.setSelection(passwordEdt.length());
                    return;
                }
                showingPasswordBtn.setImageResource(R.drawable.showing_password_icon);
                passwordEdt.setTransformationMethod(new PasswordTransformationMethod());
                passwordEdt.setSelection(passwordEdt.length());
            }
        });

        showingConfirmPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showingConfirmPasswordBtn.setSelected(!showingConfirmPasswordBtn.isSelected());
                if (showingConfirmPasswordBtn.isSelected()) {
                    showingConfirmPasswordBtn.setImageResource(R.drawable.hide_password_icon);
                    confirmPasswordEdt.setTransformationMethod(null);
                    confirmPasswordEdt.setSelection(confirmPasswordEdt.length());
                    return;
                }
                showingConfirmPasswordBtn.setImageResource(R.drawable.showing_password_icon);
                confirmPasswordEdt.setTransformationMethod(new PasswordTransformationMethod());
                confirmPasswordEdt.setSelection(confirmPasswordEdt.length());
            }
        });

    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}