package com.group0536.puzzlemazing.models.wordguessing;

import java.util.List;
import java.util.Random;

public class WordBank {
    private List<Word> vocabulary;

    /**
     * Initialize a word bank
     * @param vocabulary a list of word for the word bank
     */
    public WordBank(List<Word> vocabulary) {
        this.vocabulary = vocabulary;
    }

    /**
     * Return the vocabulary
     * @return vocabulary
     */
    public List<Word> getVocabulary() {
        return vocabulary;
    }

    /**
     * Get a word that is not been guessed by the player
     * @return a word for the player to guess guessing_next
     */
    public Word getARandomWord(){
        Random rand = new Random();
        boolean isNewWord = false;
        Word newWordForGuessing = null;
        while(!isNewWord){
            newWordForGuessing = vocabulary.get(rand.nextInt(vocabulary.size()));
            if (!newWordForGuessing.isGuessed()){
                isNewWord = true;
            }
        }
        return newWordForGuessing;
    }

    /**
     * Check if there is no more word that is not guessed
     * @return a boolean to show whether there is more word or no
     */
    public boolean noMoreWord(){
        boolean moreWord = true;
        for(Word word : vocabulary){
            if(!word.isGuessed()){
                moreWord = false;
            }
        }
        return moreWord;
    }
}
