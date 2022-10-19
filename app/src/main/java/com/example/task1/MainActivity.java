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
        symbolsInWordsReverse(wordsForAnagram, filterForAnagram);

        return buildOfAnagram(wordsForAnagram);
    }

    protected void symbolsInWordsReverse(String[] words, String filter) {
        for (int i = 0; i < words.length; i++) {
            words[i] = symbolsReverse(words[i], filter);
        }
    }

    protected String symbolsReverse(String word, String filter) {
        char[] symbols = word.toCharArray();

        for ( int i = 0, j = symbols.length - 1; i < j; i++, j--) {
            if (filterCheck(symbols[i], filter.toCharArray())) {
                i++;
            }
            if (filterCheck(symbols[j], filter.toCharArray())) {
                j--;
            }
            char tmp = symbols[i];
            symbols[i] = symbols[j];
            symbols[j] = tmp;
        }
        return new String(symbols);
    }

    protected boolean filterCheck(char symbolToCheck, char[] filterSymbols) {
        for (char filterSymbol : filterSymbols) {
            if (filterSymbol == symbolToCheck) {
                return true;
            }
        }
        return false;
    }

    protected String buildOfAnagram(String[] wordsAfterReverse) {
        String resultString = "";
        int lastIndex = wordsAfterReverse.length - 1;

        for (int i = 0; i < lastIndex; i++) {
            resultString += wordsAfterReverse[i] + " ";
        }
        resultString += wordsAfterReverse[lastIndex];

        return resultString;
    }
}
