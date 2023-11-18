package org.example;

import java.util.List;

public class PigLatinReorganizer extends PigLatin {

    public static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y');

    public PigLatinReorganizer(PigLatin next) {
        super(next);
    }

    @Override
    public PigLatin execute(String word) {
        word = reorganizeWord(word);
        return super.executeNext(word);
    }

    private String reorganizeWord(String word) {
        int indexOfFirstVowel = getIndexOfTheWordFirstVowel(word);
        if(indexOfFirstVowel == -1)
            return word;

        String firstPart = word.substring(indexOfFirstVowel);
        String lastPart = word.substring(0, indexOfFirstVowel);
        word = firstPart + lastPart;
        return word;
    }

    private int getIndexOfTheWordFirstVowel(String word) {
        char[] letters = word.toCharArray();
        for (char letter : letters)
            if (VOWELS.contains(letter)) {
                return word.indexOf(letter);
            }
        return -1;
    }
}
