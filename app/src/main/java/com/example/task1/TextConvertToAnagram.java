package com.example.task1;

public class TextConvertToAnagram {

    /**
     * start a process of converting - split text for anagram into individual words and create array of words
     * build anagram from reversed words
     *
     * @return built anagram
     */
    public static String convertToAnagram(String text, String filter) {
        final String[] words = text.split("\\s+");
        final StringBuilder anagram = new StringBuilder();
        final int lastIndex = words.length - 1;

        for (int i = 0; i < lastIndex; i++) {
            anagram.append(symbolsInWordsReverse(words[i], filter)).append(" ");
        }
        anagram.append(symbolsInWordsReverse(words[lastIndex], filter));
        return anagram.toString();
    }

    /**
     * loop directs each word to reverse
     * reverse each word taking into account filter symbols - keep position of symbols from filter
     * check each symbol - doesn't take into account digits and non alphabetic symbols
     *
     * @return built anagram
     */
    private static String symbolsInWordsReverse(String word, String filter) {
        final char[] symbols = word.toCharArray();

        for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
            if (symbolCheck(symbols[i], filter)) {
                j++;
            } else if (symbolCheck(symbols[j], filter)) {
                i--;
            } else {
                char tmp = symbols[i];
                symbols[i] = symbols[j];
                symbols[j] = tmp;
            }
        }
        return new String(symbols);
    }

    /**
     * checking symbols - comparison with symbols from filter
     *
     * @return true symbol anf filter are equal and false if no
     */
    private static boolean symbolCheck(char symbol, String filter) {
        return filter.isEmpty() ? !Character.isAlphabetic(symbol) : filter.indexOf(symbol) != -1;
    }
}
