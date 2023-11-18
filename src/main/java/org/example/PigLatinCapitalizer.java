package org.example;

public class PigLatinCapitalizer extends PigLatin{
    public PigLatinCapitalizer(PigLatin next) {
        super(next);
    }

    @Override
    public PigLatin execute(String word) {
        char[] charArray = word.toCharArray();
        if(getHasUpperCaseLetters(charArray)) {
            word = word.toLowerCase();
            String firstLetter = word.substring(0, 1);
            String lastPart = word.substring(1);
            word = firstLetter.toUpperCase() + lastPart;
        }
        return super.executeNext(word);
    }

    private boolean getHasUpperCaseLetters(char[] charArray) {
        for (char each : charArray)
            if(Character.isUpperCase(each))
                return true;
        return false;
    }
}
