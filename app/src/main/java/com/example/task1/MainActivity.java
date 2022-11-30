package com.example.task1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * link between custom toolbar and logic of program
         */
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        /**
         * link between views from xml and logic of program
         */
        TextInputEditText editTextForAnagram = findViewById(R.id.editTextForAnagram);
        TextInputEditText filterInputText = findViewById(R.id.filterInputText);
        TextView preViewOfAnagram = findViewById(R.id.preViewOfAnagram);
        TextView myAnagram = findViewById(R.id.myAnagram);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                preViewOfAnagram.setVisibility(View.INVISIBLE);
                myAnagram.setVisibility(View.VISIBLE);
                myAnagram.setText(TextConvertToAnagram.convertToAnagram(Objects.requireNonNull(editTextForAnagram.getText()).toString(), filterInputText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        /**
         * listen of text input and sent each symbol to method in TextConvertToAnagram
         */
        editTextForAnagram.addTextChangedListener(textWatcher);

        /**
         * listen of filter input and sent each symbol to method in TextConvertToAnagram to correct anagram according to filter
         */
        filterInputText.addTextChangedListener(textWatcher);

    }
}
