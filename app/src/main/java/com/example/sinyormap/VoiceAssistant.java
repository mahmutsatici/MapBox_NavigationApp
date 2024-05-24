package com.example.sinyormap;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import java.util.Locale;

public class VoiceAssistant {

    private TextToSpeech textToSpeech;
    private EditText editText;

    public VoiceAssistant(Context context, EditText editText) {
        this.editText = editText;
        textToSpeech = new TextToSpeech(context, status -> {
            if (status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(new Locale("tr_TR")); // Türkçe seslendirme
            }
        });
    }

    public void askUserDestination() {
        speak("Nereye gitmek istiyorsunuz?");
    }

    public void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void stopSpeaking() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    public void processUserInput(String destination) {
        speak("Gitmek istediğiniz yer " + destination + ".");
        editText.setText(destination);
    }
}
