package com.example.task1;

public class FieldsForTests {
    public final String input;
    public final String filter;
    public final String expectedOutput;

    FieldsForTests(String input, String filter, String expectedOutput) {
        this.input = input;
        this.filter = filter;
        this.expectedOutput = expectedOutput;
    }
}
