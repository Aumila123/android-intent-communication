package com.example.mda_2lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvMainResult;
    private String currentText = "";
    private String fullResultText = "";

    private final ActivityResultLauncher<Intent> secondActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
                if(result.getResultCode() == Activity.RESULT_OK && result.getData() !=null){
                    currentText = result.getData().getStringExtra("USER_TEXT");
                    if(currentText != null){
                        tvMainResult.setText(currentText);
                        fullResultText = currentText;
                    }
                }
            });

    private final ActivityResultLauncher<Intent> thirdActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                    fullResultText = result.getData().getStringExtra("CALCULATED_TEXT");
                    if(fullResultText != null){
                        tvMainResult.setText(fullResultText);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMainResult = findViewById(R.id.tvMainResult);
        Button btnEnterText = findViewById(R.id.btnEnterText);
        Button btnCountWords = findViewById(R.id.btnCountWords);
        Button btnSendText = findViewById(R.id.btnSendText);

        handleIncomingIntent(getIntent());

        btnEnterText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("EXISTING_TEXT", currentText);
            secondActivityLauncher.launch(intent);
        });

        btnCountWords.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            intent.putExtra("TEXT_TO_COUNT", currentText);
            thirdActivityLauncher.launch(intent);
        });

        btnSendText.setOnClickListener(v -> {
            String textToSend = tvMainResult.getText().toString();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, textToSend);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Share");
            startActivity(shareIntent);
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIncomingIntent(intent);
    }

    private void handleIncomingIntent(Intent intent) {
        if (intent != null && Intent.ACTION_SEND.equals(intent.getAction()) && "text/plain".equals(intent.getType())) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) {
                currentText = sharedText;
                fullResultText = sharedText;
                tvMainResult.setText(sharedText);
            }
        }
    }
}