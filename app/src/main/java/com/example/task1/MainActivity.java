package com.example.task1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private TextInputEditText editTextForAnagram;
    private TextInputEditText filterInputText;
    private TextView preViewOfAnagram;
    private TextView myAnagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        editTextForAnagram = findViewById(R.id.editTextForAnagram);
        filterInputText = findViewById(R.id.filterInputText);
        preViewOfAnagram = findViewById(R.id.preViewOfAnagram);
        myAnagram = findViewById(R.id.myAnagram);

        editTextForAnagram.addTextChangedListener(this);
        filterInputText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        preViewOfAnagram.setVisibility(View.INVISIBLE);
        myAnagram.setVisibility(View.VISIBLE);
        myAnagram.setText(TextConvertToAnagram.convertToAnagram(
                editTextForAnagram.getText().toString(), filterInputText.getText().toString()
        ));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
