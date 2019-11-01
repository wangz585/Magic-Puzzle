package com.puzzle.mazing.DataAccess;


import android.content.Context;

import com.puzzle.mazing.Models.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordManager {

    private ArrayList<Word> wordBank;

    public WordManager(Context currentContext) {
        this.wordBank = new ArrayList();
        CreateWordBank(currentContext);
    }

    void addWord(Word word) {
        wordBank.add(word);
    }

    void removeWord(Word word) {
        wordBank.remove(word);
    }

    void CreateWordBank(Context currentContext) {
        // We need to get access to the current context so that we can have a asset manager
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    currentContext.getAssets().open("input.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(":");
                Word newWord = new Word(words[0], words[1], this);
                wordBank.add(newWord);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    boolean inDictionary(String userInput) {
        int index = 0;
        while (index < wordBank.size()) {
            Word current = wordBank.get(index);
            if (current.getSpelling().equals(userInput) && current.isGuessed()) {
                return true;
            }
            index++;
        }
        return false;
    }

    Word createAWordForGuessing() {
        int numOfWords = wordBank.size() - 1;
        int index = 0 + (int) (Math.random() * (numOfWords - 0 + 1));
        while (wordBank.get(index).isGuessed()) {
            // if the word is already guessed by the player, change into a new index
            index = 0 + (int) (Math.random() * (numOfWords - 0 + 1));
        }
        // when Word Manager picks a word, this word is defined to be shown to the player
        Word newGenerated = wordBank.get(index);
        newGenerated.setGuessed(true);
        return newGenerated;
    }
}

