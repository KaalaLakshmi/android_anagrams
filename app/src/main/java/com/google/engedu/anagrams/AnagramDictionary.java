package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<String>();
    HashSet<String> wordSet = new HashSet<>();
    HashMap<String,ArrayList> lettersToWord = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);
            wordList.add(word);
        }
    }

    public boolean isGoodWord(String word, String base)
    {
        if(wordSet.contains(word))
        {
            if(!word.toLowerCase().contains(base.toLowerCase()))
                return true;
        }
        return false;
    }

    public ArrayList<String> getAnagrams(String targetWord)
    {
        ArrayList<String> result = new ArrayList<String>();
        String s = sortLetters(targetWord);
        String temp,s1;

        //2nd modification
        if(lettersToWord.containsKey(s))
        {//if key already present then add targetWord
            ArrayList<String> vall = new ArrayList<String>();
            vall = lettersToWord.get(s);
            vall.add(targetWord);
        }
        else
        {//creating new arraylist and key
            ArrayList<String> anew = new ArrayList<String>();
            anew.add(targetWord);
            lettersToWord.put(s,anew);

        }
        //1st modification
        result.add(targetWord);
        Iterator<String> it = wordList.iterator();
        while(it.hasNext())
        {
            temp = it.next();
            s1 = sortLetters(temp);
            if(s1.equals(s))
            {  result.add(temp);  }
        }
        return result;
    }
    public String sortLetters(String orginal)
    {
        char[] chars =orginal.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;

    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String temp,s1,extra;

        for(int i=97;i<123;i++)
        {
            char c = (char)i;
            extra=sortLetters(word+c);
            Iterator<String> it = wordList.iterator();
            while (it.hasNext()) {
                temp = it.next();
                s1 = sortLetters(temp);
                if (s1.equals(extra)) {
                    result.add(temp);
                }
            }
        }
        return result;

    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
