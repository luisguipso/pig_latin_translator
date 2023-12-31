package org.example;

import org.apache.commons.lang3.StringUtils;

public class PigLatinTranslator {

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

    private String translateWord(String word) {
        if(!hasLetters(word))
            return word;

        PigLatinReorganizer commandsChain = new PigLatinReorganizer(
                new PigLatinPostfixAppender(
                        new PigLatinCapitalizer(
                                new PigLatinPunctuator(null)
                        )));
        return commandsChain.execute(word).getWord();
    }

    private boolean hasLetters(String word) {
        for(char each: word.toCharArray())
            if(Character.isLetter(each))
                return true;
        return false;
    }
}
