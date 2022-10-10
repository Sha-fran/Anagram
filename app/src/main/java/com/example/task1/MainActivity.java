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

        editTextForAnagram.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focusOn) {
                if (focusOn == true) {
                    inputLayoutForAnagram.setHint(getString(R.string.text_for_anagram));
                } else {
                    inputLayoutForAnagram.setHint(getString(R.string.enter_text_for_anagram));
                }
            }
        });

//        editTextForAnagram.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputLayoutForAnagram.setHint(getString(R.string.text_for_anagram));
//            }
//        });

    }
}
