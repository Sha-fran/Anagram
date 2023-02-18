package com.example.task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TextConvertToAnagramTest {

    private final List<FieldsForTests> testCases = new ArrayList<FieldsForTests>() {{
        new FieldsForTests("Foxminded cool 24/7", "xl", "dexdnimoF oocl 7/42");
        new FieldsForTests("", "xl", "");
        new FieldsForTests("Foxminded cool 24/7", "", "dednimxoF looc 24/7");
    }};

    @Test
    public void anagramCasesTesting() {

        for (FieldsForTests cases : testCases) {
            assertEquals(cases.expectedOutput, TextConvertToAnagram.convertToAnagram(cases.input, cases.filter));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullInInput() {
        assertNull(TextConvertToAnagram.convertToAnagram(null, "xl"));
    }


    @Test(expected = NullPointerException.class)
    public void testNullInFilter() {
        assertNull(TextConvertToAnagram.convertToAnagram("Foxminded cool 24/7", null));
    }
}
