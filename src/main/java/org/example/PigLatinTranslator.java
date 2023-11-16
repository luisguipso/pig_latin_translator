package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class PigLatinTranslator {

    public static final String POSTFIX = "ay";
    public static final String NO_CONSONANTS_POSTFIX = "yay";


    public String translate(String text) {
        if (text == null || text.equals(""))
            return text;

        String[] words = text.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            result = result + translateWord(words[i]);

            if (i < words.length - 1)
                result = result + " ";

        }

        System.out.println("text: " + text + " result: " + result);
        return result;
    }

    private static String translateWord(String word) {
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'y');
        int index = 0;
        index = getIndex(word, vowels, index);


        //remove punctuation
        char lastCharOnTheWord = word.charAt(word.length() - 1);
        String punctuation = "";
        if (Pattern.matches("\\p{IsPunctuation}", String.valueOf(lastCharOnTheWord))) {
            punctuation = String.valueOf(word.charAt(word.length() - 1));
            word = word.substring(0, word.length() - 1);
        }

        String firstPart = word.substring(index, word.length());

        //change first letter to capital
        boolean begginsWithCapital = StringUtils.isAllUpperCase(String.valueOf(word.charAt(0)));
        word = word.toLowerCase();
        if (begginsWithCapital) {

            String firstLetter = firstPart.substring(0, 1);
            firstPart = firstPart.substring(1, firstPart.length());
            firstPart = firstLetter.toUpperCase().concat(firstPart);
        }

        String lastPart = word.substring(0, index).concat(POSTFIX);
        if (punctuation != null && !punctuation.equals(""))
            lastPart = lastPart.concat(punctuation);

        String result = firstPart.concat(lastPart);
        return result;
    }

    private static int getIndex(String word, List<Character> vowels, int index) {
        for (char each : word.toCharArray()) {
            for (Character eachVowel : vowels) {
                if (eachVowel == each) {
                    return word.indexOf(eachVowel);
                }
            }
        }
        return index;
    }
}
