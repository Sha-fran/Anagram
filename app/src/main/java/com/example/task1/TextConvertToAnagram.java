package com.example.task1;

public class TextConvertToAnagram {

    /**
     * start a process of converting - split text for anagram into individual words and create array of words
     * build anagram from reversed words
     * @return built anagram
     */
    public static String convertToAnagram(String text, String filter) {
        String[] words = text.split("\\s+");
        StringBuilder anagrama = new StringBuilder();
        int lastIndex = words.length - 1;

        for (int i = 0; i < lastIndex; i++) {
            anagrama.append(symbolsInWordsReverse(words[i], filter)).append(" ");
        }
        anagrama.append(symbolsInWordsReverse(words[lastIndex], filter));

        return anagrama.toString();
    }

    /**
     * loop directs each word to reverse
     * reverse each word taking into account filter symbols - keep position of symbols from filter
     * check each symbol - doesn't take into account digits and non alphabetic symbols
     *
     * @return built anagram
     */
    private static String symbolsInWordsReverse(String word, String filter) {
        char[] symbols = word.toCharArray();

        for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
            while (symbolCheck(symbols[i], filter) && i < j) {
                i++;
            }
            while (symbolCheck(symbols[j], filter) && i < j) {
                j--;
            }

            char tmp = symbols[i];
            symbols[i] = symbols[j];
            symbols[j] = tmp;
        }

        return new String(symbols);
    }

    /**
     * checking symbols - comparison with symbols from filter
     *
     * @return true symbol anf filter are equal and false if no
     */
    private static boolean symbolCheck(char symbolToCheck, String filter) {
        if (filter.isEmpty()) {
            return !Character.isAlphabetic(symbolToCheck);
        }
        return filter.lastIndexOf(symbolToCheck) != -1;
    }
}
