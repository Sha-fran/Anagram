package com.example.task1;

public class TextConvertToAnagram {

    /**
     * start a process of converting - split text for anagram into individual words and create array of words
     *
     * @return built anagram
     */
    public static String convertToAnagram(String text, String filter) {
        String[] words = text.split("\\s+");

        return symbolsInWordsReverse(words, filter);
    }

    /**
     * loop directs each word to reverse
     * reverse each word taking into account filter symbols - keep position of symbols from filter
     * check each symbol - doesn't take into account digits and non alphabetic symbols
     * building anagram from reversed words     *
     * @return built anagram
     */
    private static String symbolsInWordsReverse(String[] words, String filter) {
        StringBuilder anagrama = new StringBuilder();
        int lastIndex = words.length - 1;

        for (int k = 0; k < words.length; k++) {
            char[] symbols = words[k].toCharArray();

            for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
                if (filter.isEmpty()) {
                    if (!Character.isAlphabetic(symbols[i])) {
                        i++;
                    }
                    if (!Character.isAlphabetic(symbols[i]) && j > i) {
                        j--;
                    }

                } else {
                    if (filterCheck(symbols[i], filter)) {
                        i++;
                    }
                    if (filterCheck(symbols[j], filter)) {
                        j--;
                    }
                }
                char tmp = symbols[i];
                symbols[i] = symbols[j];
                symbols[j] = tmp;
            }
            words[k] = new String(symbols);

            if (k < lastIndex) {
                anagrama.append(words[k]).append(" ");
            } else {
                anagrama.append(words[k]);
            }
        }
        return anagrama.toString();
    }

    /**
     * checking symbols - comparison with symbols from filter
     *
     * @return true symbol anf filter are equal and false if no
     */
    private static boolean filterCheck(char symbolToCheck, String filter) {
        return filter.lastIndexOf(symbolToCheck) != -1;
    }
}
