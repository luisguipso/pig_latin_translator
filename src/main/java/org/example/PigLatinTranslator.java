package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class PigLatinTranslator {

    public static final String POSTFIX = "ay";
    public static final String NO_CONSONANTS_POSTFIX = "yay";
    public static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y');


    public String translate(String text) {
        if (StringUtils.isBlank(text))
            return text;

        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < words.length; index++) {
            result.append(translateWord(words[index]));
            if (isNotTheLastWord(words, index))
                result.append(" ");
        }
        return result.toString();
    }

    private static boolean isNotTheLastWord(String[] words, int index) {
        return index != words.length - 1;
    }

    private static String translateWord(String word) {
        int indexOfFirstVowel = getIndexOfFirstVowel(word);
        if (indexOfFirstVowel == -1)
            return word;

        boolean wasFirstLetterCapital = isFirstLetterCapital(word);
        word = word.toLowerCase();

        //remove punctuation
        String punctuation = null;
        boolean wasEndedByPunctuation = isEndedByPunctuation(word);
        if (wasEndedByPunctuation) {
            punctuation = String.valueOf(getLastLetter(word));
            word = removeLastChar(word);
        }

        String result = rearrangeWord(word, indexOfFirstVowel);

        if (wasEndedByPunctuation)
            result = result + punctuation;

        if (wasFirstLetterCapital)
            result = makeNewFirstLetterCapital(result);

        return result;
    }

    private static String rearrangeWord(String word, int indexOfFirstVowel) {
        String firstPart = word.substring(indexOfFirstVowel);
        String lastPart = word.substring(0, indexOfFirstVowel);
        return firstPart + lastPart + POSTFIX;
    }

    private static int getIndexOfFirstVowel(String word) {
        char[] letters = word.toCharArray();
        for (char letter : letters)
            if (VOWELS.contains(letter))
                return word.indexOf(letter);
        return -1;
    }

    private static boolean isFirstLetterCapital(String word) {
        return StringUtils.isAllUpperCase(String.valueOf(word.charAt(0)));
    }

    private static boolean isEndedByPunctuation(String word) {
        String lastLetter = String.valueOf(getLastLetter(word));
        return Pattern.matches("\\p{IsPunctuation}", lastLetter);
    }

    private static String removeLastChar(String word) {
        return word.substring(0, word.length() - 1);
    }

    private static String makeNewFirstLetterCapital(String word) {
        String firstLetter = word.substring(0, 1);
        word = removeFirstLetter(word);
        word = firstLetter.toUpperCase() + word;
        return word;
    }

    private static String removeFirstLetter(String word) {
        return word.substring(1);
    }

    private static char getLastLetter(String word) {
        return word.charAt(word.length() - 1);
    }
}
