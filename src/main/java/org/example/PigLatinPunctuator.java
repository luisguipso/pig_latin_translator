package org.example;

public class PigLatinPunctuator extends PigLatin {
    public PigLatinPunctuator(PigLatin next) {
        super(next);
    }

    @Override
    public PigLatin execute(String word) {
        int indexOfPunctuation = getIndexOfPunctuation(word);
        if(indexOfPunctuation != -1) {
            char punctuation = word.charAt(indexOfPunctuation);
            word = word.replace(String.valueOf(punctuation), "");
            word = word + punctuation;
        }
        return super.executeNext(word);
    }

    private int getIndexOfPunctuation(String word) {
        for(char each : word.toCharArray())
            if(!Character.isLetter(each) && !Character.isDigit(each))
                return word.indexOf(each);
        return -1;
    }
}
