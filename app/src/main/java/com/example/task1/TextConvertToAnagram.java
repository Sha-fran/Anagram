package com.example.task1;

public class TextConvertToAnagram {

    /**
     * start a process of converting - split text for anagram into individual words and create array of words
     * @return built anagram
     */
    public static String convertToAnagram(String text, String filter) {
        String[] words = text.split("\\s+");
        symbolsInWordsReverse(words, filter);

        return buildOfAnagram(words);
    }

    /**
     * loop directs each word to reverse
     * reverse each word taking into account filter symbols - keep position of symbols from filter
     * check each symbol - doesn't take into account digits and non alphabetic symbols
     * @return reversed and filtered word
     */
    private static void symbolsInWordsReverse(String[] words, String filter) {
        for (int k = 0; k < words.length; k++) {
            char[] symbols = words[k].toCharArray();

            if (filter.isEmpty()) {
                for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
                    if (!Character.isAlphabetic(symbols[i])) {
                        i++;
                    }
                    if (!Character.isAlphabetic(symbols[i]) && j > i) {
                        j--;
                    }
                    char tmp = symbols[i];
                    symbols[i] = symbols[j];
                    symbols[j] = tmp;
                }
            } else {
                for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
                    if (filterCheck(String.valueOf(symbols[i]), filter)) {
                        i++;
                    }
                    if (filterCheck(String.valueOf(symbols[j]), filter)) {
                        j--;
                    }
                    char tmp = symbols[i];
                    symbols[i] = symbols[j];

                    symbols[j] = tmp;
                }
            }
            words[k] = new String(symbols);
        }
    }

    /**
     * checking symbols - comparison with symbols from filter
     * @return true symbol anf filter are equal and false if no
     */
    private static boolean filterCheck(String symbolToCheck, String filter) {
        return filter.contains(symbolToCheck);
    }

    /**
     * building anagram from reversed words
     * @return built anagram
     */
    private static String buildOfAnagram(String[] wordsAfterReverse) {
        StringBuilder resultString = new StringBuilder();
        int lastIndex = wordsAfterReverse.length - 1;

        for (int i = 0; i < lastIndex; i++) {
            resultString.append(wordsAfterReverse[i]).append(" ");
        }
        resultString.append(wordsAfterReverse[lastIndex]);

        return resultString.toString();
    }
}
