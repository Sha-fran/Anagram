package com.example.task1;

import com.example.task1.UserModel;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String anagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserModel viewModel = ViewModelProvider.of(this).get(UserModel.class);
        viewModel.userLiveData.observer(this, new Observer() {
            @Override
                public void onChangeed(@Nullable User data){
            }
        });

        TextInputLayout inputLayoutForAnagram = findViewById(R.id.textInputLayotForAnagram);
        TextInputEditText editTextForAnagram = findViewById(R.id.editTextForAnagram);
        TextInputEditText filterInputText = findViewById(R.id.filterInputText);
        TextView preViewOfAnagram = findViewById(R.id.preViewOfAnagram);
        TextView myAnagram = findViewById(R.id.myAnagram);

        if (savedInstanceState != null) {
            String savedAnagram = savedInstanceState.getString("myAnagram");
            myAnagram.setText(savedAnagram);
            preViewOfAnagram.setVisibility(View.INVISIBLE);
            myAnagram.setVisibility(View.VISIBLE);
//            if (savedAnagram.length() > 0) {
//                editTextForAnagram.setGravity(Gravity.START);
//            }
        }

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

        editTextForAnagram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                preViewOfAnagram.setVisibility(View.INVISIBLE);
                myAnagram.setVisibility(View.VISIBLE);
                myAnagram.setText(convertToAnagram(Objects.requireNonNull(editTextForAnagram.getText()).toString(), Objects.requireNonNull(filterInputText.getText()).toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        filterInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myAnagram.setText(convertToAnagram(Objects.requireNonNull(editTextForAnagram.getText()).toString(), Objects.requireNonNull(filterInputText.getText()).toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

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

        if (filter.isEmpty()) {
            for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
                while (checkUpperCaseSymbol(symbols[i]) && checkUpperLowerSymbol(symbols[i]) && i < j) {
                    i++;
                }
                while (checkUpperCaseSymbol(symbols[j]) && checkUpperLowerSymbol(symbols[j]) && j > i) {
                    j--;
                }
                char tmp = symbols[i];
                symbols[i] = symbols[j];
                symbols[j] = tmp;
            }
        } else {
            for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
                while (filterCheck(symbols[i], filter.toCharArray()) && i < j) {
                    i++;
                }
                while (filterCheck(symbols[j], filter.toCharArray()) && j > i) {
                    j--;
                }
                char tmp = symbols[i];
                symbols[i] = symbols[j];
                symbols[j] = tmp;
            }
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

    protected boolean checkUpperCaseSymbol(char check) {
        return check < 'A' || check > 'Z';
    }

    protected boolean checkUpperLowerSymbol(char check) {
        return check < 'a' || check > 'z';
    }

    protected String buildOfAnagram(String[] wordsAfterReverse) {
        StringBuilder resultString = new StringBuilder();
        int lastIndex = wordsAfterReverse.length - 1;

        for (int i = 0; i < lastIndex; i++) {
            resultString.append(wordsAfterReverse[i]).append(" ");
        }
        resultString.append(wordsAfterReverse[lastIndex]);

        return resultString.toString();
    }

    protected void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("myAnagram", anagram);
    }
}


















































