package com.example.necomovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    private ConstraintLayout parentLayout;
    private EditText emailEdt;
    private EditText passwordEdt;
    private ImageButton showingPasswordBtn;
    private CheckBox rememberMeCB;
    private TextView forgotPasswordTv;
    private Button signInBtn;
    private TextView signUpTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        parentLayout = findViewById(R.id.parentLayout);
        emailEdt = (EditText) findViewById(R.id.emailEdt);
        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        showingPasswordBtn = (ImageButton) findViewById(R.id.showingPasswordBtn);
        rememberMeCB = (CheckBox) findViewById(R.id.checkBox);
        forgotPasswordTv = (TextView) findViewById(R.id.forgotPasswordTv);
        signInBtn = (Button) findViewById(R.id.signInBtn);
        signUpTv = (TextView) findViewById(R.id.signUpTv);
        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Hide the soft keyboard when the parent layout is touched
                hideSoftKeyboard();
                return false;
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
        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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