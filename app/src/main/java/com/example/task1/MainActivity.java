package com.example.task1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout inputLayoutForAnagram = findViewById(R.id.textInputLayotForAnagram);
        TextInputEditText editTextForAnagram = findViewById(R.id.editTextForAnagram);
//        TextInputLayout filterInputlayot = findViewById(R.id.filterInputlayot);
        TextInputEditText filterInputText = findViewById(R.id.filterInputText);
        TextView preViewOfAnagram = findViewById(R.id.preViewOfAnagram);
        TextView myAnagram = findViewById(R.id.myAnagram);
        Button convertButton = findViewById(R.id.convertButton);

        editTextForAnagram.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focusOn) {
                if (focusOn) {
                    inputLayoutForAnagram.setHint(getString(R.string.text_for_anagram));
                    editTextForAnagram.setGravity(Gravity.START);
                } else {
                    inputLayoutForAnagram.setHint(getString(R.string.enter_text_for_anagram));
                    editTextForAnagram.setGravity(Gravity.CLIP_VERTICAL|Gravity.CENTER_HORIZONTAL);
                    view.requestLayout();
                }
            }
        });

        filterInputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focusOn) {
                if (focusOn) {
                    filterInputText.setGravity(Gravity.START);
                } else {
                    filterInputText.setGravity(Gravity.CLIP_VERTICAL|Gravity.CENTER_HORIZONTAL);
                    view.requestLayout();
                }
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAnagram.setText(convertToAnagram(Objects.requireNonNull(editTextForAnagram.getText()).toString(), Objects.requireNonNull(filterInputText.getText()).toString()));
            }
        });
    }

    protected String convertToAnagram(String textForAnagram, String filterForAnagram) {
        String[] wordsForAnagram = textForAnagram.split(" ");
        wordsReverse(wordsForAnagram);
        symbolsInWordsReverse(wordsForAnagram, filterForAnagram);

        return buildOfAnagram(wordsForAnagram);
    }

    protected void wordsReverse(String[] words) {
        for (int i = 0, j = words.length - 1; i < words.length; i++, j--) {
            String tmp = words[i];
            words[i] = words[j];
            words[j] = tmp;
        }
    }

    protected void symbolsInWordsReverse(String[] words, String filter) {
        for (int i = 0; i < words.length; i++) {
            symbolsReverse(words[i], filter);
        }
    }

    protected void symbolsReverse(String word, String filter) {
        char[] symbols = word.toCharArray();

        for ( int i = 0, j = symbols.length - 1; i < symbols.length; i++) {
            if (filterCheck(symbols[i], filter.toCharArray())) {
                i++;
            } else {
                char tmp = symbols[i];
                symbols[i] = symbols[j];
                symbols[j] = tmp;
            }
        }
    }

    protected boolean filterCheck(char symbolToCheck, char[] filterSymbols) {
        for (int i = 0; i < filterSymbols.length; i++) {
            if (filterSymbols[i] == symbolToCheck) {
                return true;
            }
        }
        return false;
    }

    protected String buildOfAnagram(String[] wordsAfterReverse) {
        return Arrays.toString(wordsAfterReverse);
    }
}
