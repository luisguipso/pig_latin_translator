package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class PigLatinWordTranslator {

    public static final String POSTFIX = "ay";
    public static final String NO_CONSONANTS_POSTFIX = "yay";
    public static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y');
    private String word;
    private int indexOfFirstVowel = -1;
    private boolean wasFirstLetterCapital;
    private boolean wasEndedByPunctuation;

    PigLatinWordTranslator(String word) {
        this.word = word;
        setIndexOfFirstVowel();
        setWasFirstLetterCapital();
        setWasEndedByPunctuation();
    }

    public String translate() {
        if (indexOfFirstVowel == -1)
            return word;

        if (!hasConsonants())
            return word + NO_CONSONANTS_POSTFIX;

        word = word.toLowerCase();
        String punctuation = removePunctuation();
        String result = rearrangeWord();
        result = addPunctuation(punctuation, result);
        result = makeNewFirstLetterCapital(result);
        return result;
    }

    private boolean hasConsonants() {
        for (char letter : word.toCharArray())
            if (!VOWELS.contains(letter) && Character.isLetter(letter))
                return true;
        return false;
    }


    private String addPunctuation(String punctuation, String result) {
        if (wasEndedByPunctuation)
            result = result + punctuation;
        return result;
    }

    private String removePunctuation() {
        String punctuation = null;
        if (wasEndedByPunctuation) {
            punctuation = String.valueOf(getLastLetter());
            word = removeLastChar(word);
        }
        return punctuation;
    }

    private String rearrangeWord() {
        String firstPart = word.substring(indexOfFirstVowel);
        String lastPart = word.substring(0, indexOfFirstVowel);
        return firstPart + lastPart + POSTFIX;
    }

    private void setIndexOfFirstVowel() {
        char[] letters = word.toCharArray();
        for (char letter : letters)
            if (VOWELS.contains(letter)) {
                indexOfFirstVowel = word.indexOf(letter);
                return;
            }
    }

    private void setWasFirstLetterCapital() {
        wasFirstLetterCapital = StringUtils.isAllUpperCase(String.valueOf(word.charAt(0)));
    }

    private void setWasEndedByPunctuation() {
        String lastLetter = String.valueOf(getLastLetter());
        wasEndedByPunctuation = Pattern.matches("\\p{IsPunctuation}", lastLetter);
    }

    private static String removeLastChar(String word) {
        return word.substring(0, word.length() - 1);
    }

    private String makeNewFirstLetterCapital(String word) {
        if (!wasFirstLetterCapital)
            return word;
        String firstLetter = word.substring(0, 1);
        word = removeFirstLetter(word);
        word = firstLetter.toUpperCase() + word;
        return word;
    }

    private static String removeFirstLetter(String word) {
        return word.substring(1);
    }

    private char getLastLetter() {
        return word.charAt(word.length() - 1);
    }
}
