package com.example.mda_2lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private TextView tvThirdResult;
    private String rawText = "";
    private String formattedResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvThirdResult = findViewById(R.id.tvThirdResult);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        rawText = getIntent().getStringExtra("TEXT_TO_COUNT");
        if (rawText == null) rawText = "";

        tvThirdResult.setText(rawText);

        btnCalculate.setOnClickListener(v -> {
            int wordCount = countWords(rawText);

            formattedResult = "Sakinyje \"" + rawText + "\" yra " + wordCount + " žodžiai";
            tvThirdResult.setText(formattedResult);

            Intent data = new Intent();
            data.putExtra("CALCULATED_TEXT", formattedResult);
            setResult(Activity.RESULT_OK, data);
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (formattedResult.isEmpty()) {
                    Intent data = new Intent();
                    data.putExtra("CALCULATED_TEXT", rawText);
                    setResult(Activity.RESULT_OK, data);
                }
                finish();
            }
        });
    }
    private int countWords(String s) {
        String trimmed = s.trim();
        if (trimmed.isEmpty()) return 0;
        String[] words = trimmed.split("\\s+");
        return words.length;
    }
}
