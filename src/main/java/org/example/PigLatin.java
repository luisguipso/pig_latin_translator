package org.example;

public abstract class PigLatin {

    private PigLatin next;
    private String word;
    public abstract PigLatin execute(String word);

    protected PigLatin executeNext(String word) {
        this.word = word;
        if(next != null)
            return next.execute(word);
        return this;
    }

    public String getWord(){
        return word;
    }

    public PigLatin(PigLatin next){
        this.next = next;
    }
}
