package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class BreakingEnigma {

    private final String salt = "ABCDEFGHIJKLM";
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static HashMap<Character, Character> hashMap;

    public List<char[]> enhancedCeaserCifer(List<char[]> plugedWords) {
        ArrayList<String> ceaserWords= new ArrayList<>();

        for(char[] c : plugedWords){
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(c));

            for(int rot = 2; rot < alphabet.length(); rot++){
                for(int inc = 5; inc < alphabet.length(); inc ++){
                    String stringChanged = "";
                    for(int ch = 0; ch < stringBuilder.length(); ch ++){
                        int resultInc = ch * inc;
                        int change = rot + resultInc;

                        //saber o indice do caractere no alfabeto
                        int indexOfAlphabet = alphabet.indexOf(stringBuilder.charAt(ch));
                        //saber o indice do caractere para que deve ser alterado o caracter
                        int index = (indexOfAlphabet + change) % 26;
                        //posicao do caractere no alfabeto para ser colocado na string encriptada
                        char charToChangeOnString = alphabet.charAt(index);
                        //fazer a string
                        stringChanged += charToChangeOnString;
                    }
                    ceaserWords.add(stringChanged);
                }
            }
        }
        System.out.println("Depois do ceaser: " + ceaserWords.size());

        return plugboard(ceaserWords);
    }

    public List<char[]> plugboard(List<String> wordsWithSalt){
        char[] charArray = new char[0];
        ArrayList<char[]> plugedWords = new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put('A','J');
        hashMap.put('T','D');
        hashMap.put('I','Q');
        hashMap.put('U','C');
        hashMap.put('O','X');
        hashMap.put('N', 'P');
        hashMap.put('Y', 'B');
        hashMap.put('E', 'H');
        hashMap.put('G', 'K');
        hashMap.put('L', 'S');
        int cont = 0;

        for(String s : wordsWithSalt){
            charArray = s.toCharArray();
            for(int i = 0; i < charArray.length; i++){
                for(Character c : hashMap.keySet()){
                    if(c.charValue() == s.charAt(i)){
                        Array.setChar(charArray,i,hashMap.get(c));
                    }
                }
            }
            //System.out.println(charArray);
            plugedWords.add(charArray);
            cont ++;
        }
        System.out.println("Depois da plugboard: " + cont);

        return plugedWords;
    }

    public List<String> putSalt(List<String>words){
        String combinationFront;
        String combinationBack;
        List<String> wordsWithSalt = new ArrayList<>();

        for(String s : words){
            for (int i = 0; i < salt.length(); i++) {
                char char1 = salt.charAt(i);

                for (int j = 0; j < salt.length(); j++) {
                    char char2 = salt.charAt(j);
                    combinationFront = s + char1 + char2;
                    combinationBack = char1 + String.valueOf(char2) + s;

                    wordsWithSalt.add(combinationFront);
                    wordsWithSalt.add(combinationBack);
                }
            }
        }
        System.out.println("Com o salt: " + wordsWithSalt.size());
        return wordsWithSalt;
    }

    public ArrayList<String> insertWordsIntoArrayList() throws IOException {
        ArrayList<String> words = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader("/Users/utilizador/Downloads/wordlist-2.txt"))) {
            while (s.hasNext()) {
                words.add(s.nextLine());
            }
        }
        return words;
    }
}



