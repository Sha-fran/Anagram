package com.example.task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.ArrayList;

public class TextConvertToAnagramTest {
    private final FieldsForTests case1 = new FieldsForTests();
    private final FieldsForTests case2 = new FieldsForTests();
    private final FieldsForTests case3 = new FieldsForTests();
    private final ArrayList<FieldsForTests> testCases = new ArrayList<>();

    private final FieldsForTests caseNullInInput = new FieldsForTests();

    @Test
    public void anagramCasesTesting() {
        case1.setInput("Foxminded cool 24/7");
        case1.setFilter("xl");
        case1.setExpectedOutput("dexdnimoF oocl 7/42");

        case2.setInput("");
        case2.setFilter("xl");
        case2.setExpectedOutput("");

        case3.setInput("Foxminded cool 24/7");
        case3.setFilter("");
        case3.setExpectedOutput("dednimxoF looc 24/7");

        testCases.add(case1);
        testCases.add(case2);
        testCases.add(case3);

        for (FieldsForTests cases: testCases) {
            assertEquals(cases.getExpectedOutput(), TextConvertToAnagram.convertToAnagram(cases.getInput(), cases.getFilter()));
        }
    }
    @Test
    public void testNullInInput() {
        assertNull(caseNullInInput.getExpectedOutput(), TextConvertToAnagram.convertToAnagram(null, "xl"));
    }
    @Test
    public void testNullInFilter() {
        assertNull(TextConvertToAnagram.convertToAnagram("Foxminded cool 24/7", null));
    }
}
