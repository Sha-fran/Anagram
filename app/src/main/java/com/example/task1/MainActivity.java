package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout inputLayoutForAnagram = findViewById(R.id.textInputLayotForAnagram);
        TextInputEditText editTextForAnagram = findViewById(R.id.editTextForAnagram);

        //I thought, that click on view for start of writing an anagram is a click, so used this method
        editTextForAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextForAnagram.setHint(getString(R.string.text_for_anagram));
            }
        });

    }
}
