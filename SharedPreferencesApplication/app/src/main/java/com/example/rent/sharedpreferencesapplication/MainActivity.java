package com.example.rent.sharedpreferencesapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFERENCES_FILE = "com.sda.preferences.misc";
    public static final String PREFERENCE_KEY = "ourPreference";

    private EditText input;
    private Button saveBtn;
    private Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);
        saveBtn = (Button) findViewById(R.id.button_save);
        loadBtn = (Button) findViewById(R.id.button_load);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueFromInput = input.getText().toString();

                saveToSharedPreferences(valueFromInput);
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueToSet = getStringFromSharedPreferences();

                input.setText(valueToSet);
            }
        });
    }

    private void saveToSharedPreferences(String textToSave) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        sharedPreferencesEditor.putString(PREFERENCE_KEY, textToSave);

        sharedPreferencesEditor.commit();
        Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    private String getStringFromSharedPreferences() {
        String valueToReturn = null;

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        valueToReturn = sharedPreferences.getString(PREFERENCE_KEY, "No Value");

        Toast.makeText(getApplicationContext(), "Data read", Toast.LENGTH_SHORT).show();
        return valueToReturn;
    }
}