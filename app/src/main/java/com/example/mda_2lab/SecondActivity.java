package com.example.mda_2lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etInput = findViewById(R.id.etInput);
        Button btnConfirmInput = findViewById(R.id.btnConfirmInput);

        String existingText = getIntent().getStringExtra("EXISTING_TEXT");
        if (existingText != null) {
            etInput.setText(existingText);
            etInput.setSelection(etInput.getText().length());
        }

        btnConfirmInput.setOnClickListener(v -> returnResultAndFinish());

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                returnResultAndFinish();
            }
        });
    }

    private void returnResultAndFinish() {
        String enteredText = etInput.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("USER_TEXT", enteredText);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
