package com.example.task1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.material.textfield.TextInputEditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)

public class MaiActivityTest {
    private final List<Integer> uiTestCases = new ArrayList<Integer>() {{
        Integer.valueOf(R.id.editTextForAnagram);
        Integer.valueOf(R.id.filterInputText);
        Integer.valueOf(R.id.preViewOfAnagram);
        Integer.valueOf(R.id.myAnagram);
    }};

    @Test
    public void editTextForAnagramIsDisplayed() {
        for (int cases : uiTestCases) {
            onView(withId(cases)).check(matches(isDisplayed()));
        }
    }
}
