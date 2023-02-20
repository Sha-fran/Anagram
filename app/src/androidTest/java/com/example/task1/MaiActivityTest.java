package com.example.task1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)

public class MaiActivityTest {
    private final List<FieldsForUITests> uiTestCases = new ArrayList<FieldsForUITests>() {{
        new FieldsForUITests("Foxminded cool 24/7", "xl", "dexdnimoF oocl 7/42");
        new FieldsForUITests("", "xl", "");
        new FieldsForUITests("Foxminded cool 24/7", "", "dednimxoF looc 24/7");
    }};

    @Test
    public void desctopElementIsDisplayed() {
        for (FieldsForUITests cases : uiTestCases) {
            onView(withId(R.id.editTextForAnagram)).perform(typeTextIntoFocusedView(cases.input));
            onView(withId(R.id.filterInputText)).perform(typeTextIntoFocusedView(cases.filter));
            onView(withId(R.id.myAnagram)).check(matches(withText(cases.expectedOutput)));
        }
    }
}
