package com.example.task1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

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
                myAnagram.setText(TextConvertToAnagram.convertToAnagram(editTextForAnagram.getText().toString(), filterInputText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextForAnagram.addTextChangedListener(textWatcher);
        filterInputText.addTextChangedListener(textWatcher);
    }
}
