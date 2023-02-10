package com.example.task1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextConvertToAnagramTest {
    String textForAnagram, filter;

    @Test
    public void AnagramWithoutFilter() {
        textForAnagram = "Foxminded cool 24/7";
        filter = "";

        assertEquals("dednimxoF looc 24/7", TextConvertToAnagram.convertToAnagram(textForAnagram, filter));
    }

    @Test
    public void AnagramWithtFilter() {
        textForAnagram = "Foxminded cool 24/7";
        filter = "xl";

        assertEquals("dexdnimoF oocl 7/42", TextConvertToAnagram.convertToAnagram(textForAnagram, filter));
    }

}
