package com.example.task1;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link between custom toolbar and logic of program
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        // link between views from xml and logic of program
        TextInputLayout inputLayoutForAnagram = findViewById(R.id.textInputLayotForAnagram);
        TextInputEditText editTextForAnagram = findViewById(R.id.editTextForAnagram);
        TextInputEditText filterInputText = findViewById(R.id.filterInputText);
        TextView preViewOfAnagram = findViewById(R.id.preViewOfAnagram);
        TextView myAnagram = findViewById(R.id.myAnagram);

//      redefining state of view after rotation
        if (savedInstanceState != null) {
            preViewOfAnagram.setVisibility(View.INVISIBLE);
            myAnagram.setVisibility(View.VISIBLE);
            editTextForAnagram.setGravity(Gravity.START);
            filterInputText.setGravity(Gravity.START);
            editTextForAnagram.setTextSize(22);
            filterInputText.setTextSize(22);
        }

        //change state of view editTextForAnagram despite of focus
        editTextForAnagram.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focusOn) {
                if (focusOn || Objects.requireNonNull(editTextForAnagram.getText()).toString().length() > 0) {
                    editTextForAnagram.setTextSize(22);
                    inputLayoutForAnagram.setHint(getString(R.string.text_for_anagram));
                    editTextForAnagram.setGravity(Gravity.START);
                } else {
                    editTextForAnagram.setTextSize(16);
                    inputLayoutForAnagram.setHint(getString(R.string.enter_text_for_anagram));
                    editTextForAnagram.setGravity(Gravity.CLIP_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    view.requestLayout();
                    closeKeyboard();
                }
            }
        });

        //change state of view filterInputText despite of focus
        filterInputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focusOn) {
                if (focusOn || Objects.requireNonNull(filterInputText.getText()).toString().length() > 0) {
                    filterInputText.setGravity(Gravity.START);
                    filterInputText.setTextSize(22);
                } else {
                    filterInputText.setGravity(Gravity.CLIP_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    filterInputText.setTextSize(16);
                    view.requestLayout();
                    closeKeyboard();
                }
            }
        });

        //listen of text input and sent each symbol to method in TextConvertToAnagram
        editTextForAnagram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                preViewOfAnagram.setVisibility(View.INVISIBLE);
                myAnagram.setVisibility(View.VISIBLE);
                myAnagram.setText(TextConvertToAnagram.convertToAnagram(Objects.requireNonNull(editTextForAnagram.getText()).toString(), Objects.requireNonNull(filterInputText.getText()).toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //listen of filter input and sent each symbol to method in TextConvertToAnagram to correct anagram according to filter
        filterInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myAnagram.setText(TextConvertToAnagram.convertToAnagram(Objects.requireNonNull(editTextForAnagram.getText()).toString(), Objects.requireNonNull(filterInputText.getText()).toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //hide keyboard
    protected void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
