package com.example.sinyormap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SearchActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "org.tcellgy.android.kitaplik.MESAJ";
    private VoiceAssistant voiceAssistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText = findViewById(R.id.editTextText);
        voiceAssistant = new VoiceAssistant(this, editText);
        voiceAssistant.askUserDestination();
    }

    public void onClickBul(View view) {
        EditText editText = findViewById(R.id.editTextText);
        String mesaj = editText.getText().toString();
        voiceAssistant.processUserInput(mesaj);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, mesaj);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (voiceAssistant != null) {
            voiceAssistant.onDestroy();
        }
    }
}
