package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class PigLatinTranslator {

    public static final String POSTFIX = "ay";
    public static final String NO_CONSONANTS_POSTFIX = "yay";
    public static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y');


    public String translate(String text) {
        if (text == null || text.equals(""))
            return text;

        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            result.append(translateWord(words[i]));

            if (i < words.length - 1)
                result.append(" ");

        }

        System.out.println("text: " + text + " result: " + result.toString());
        return result.toString();
    }

    private static String translateWord(String word) {
        int index = getIndex(word, VOWELS);
        if(index == -1)
            return word;

        //remove punctuation
        char lastCharOnTheWord = word.charAt(word.length() - 1);
        String punctuation = "";
        if (Pattern.matches("\\p{IsPunctuation}", String.valueOf(lastCharOnTheWord))) {
            punctuation = String.valueOf(word.charAt(word.length() - 1));
            word = word.substring(0, word.length() - 1);
        }

        String firstPart = word.substring(index, word.length());

        //change first letter to capital

        if (isFirstLetterCapital(word)) {
            word = word.toLowerCase();
            String firstLetter = firstPart.substring(0, 1);
            firstPart = firstPart.substring(1, firstPart.length());
            firstPart = firstLetter.toUpperCase().concat(firstPart);
        }

        String lastPart = word.substring(0, index).concat(POSTFIX);
        if (!StringUtils.isBlank(punctuation))
            lastPart = lastPart.concat(punctuation);

        String result = firstPart.concat(lastPart);
        return result;
    }

    private static boolean isFirstLetterCapital(String word) {
        return StringUtils.isAllUpperCase(String.valueOf(word.charAt(0)));
    }

    private static int getIndex(String word, List<Character> vowels) {
        for (char each : word.toCharArray()) {
            for (Character eachVowel : vowels) {
                if (eachVowel == each) {
                    return word.indexOf(eachVowel);
                }
            }
        }
        return -1;
    }
}
