package org.example;

import java.util.Arrays;
import java.util.List;

public class PigLatinPostfixAppender extends PigLatin{

    public static final String POSTFIX = "ay";
    public static final String NO_CONSONANTS_POSTFIX = "yay";

    public static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y');


    public PigLatinPostfixAppender(PigLatin next) {
        super(next);
    }

    @Override
    public PigLatin execute(String word) {
        if(!hasConsonants(word))
            word = word + NO_CONSONANTS_POSTFIX;
        else
            word = word + POSTFIX;

        return super.executeNext(word);
    }

    private boolean hasConsonants(String word) {
        for (char letter : word.toCharArray())
            if (!VOWELS.contains(letter) && Character.isLetter(letter))
                return true;
        return false;
    }
}
