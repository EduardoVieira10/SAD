package org.example;

import com.google.common.hash.Hashing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Otimização {

    private final String salt = "ABCDEFGHIJKLM";
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static HashMap<Character, Character> hashMap;

    public String methodForReadPath(String givenHash) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        String hash = null;
        int count = 0;
        try (Scanner s = new Scanner(new FileReader("/Users/utilizador/Downloads/wordlist-2.txt"))) {
            while (s.hasNext()) {
                words.add(s.nextLine());
            }
        }
        //ArrayList<String> wordWithSalt = null;
        for(String s : words){
            //WORDS WITH SALT
            ArrayList<String> wordWithSalt = putSalt(s);

            //WORDS PLUGED
            ArrayList<char[]> wordsPluged = plugboard(wordWithSalt);

            for(char[] c : wordsPluged){
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(c));
                String stringChanged = "";

                for(int rot = 0; rot < alphabet.length(); rot++){
                    for(int inc = 0; inc < alphabet.length(); inc ++){

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
                        hash = String.valueOf(Hashing.sha512().hashString(stringChanged, StandardCharsets.UTF_8));
                        if(hash.equals(givenHash)){
                            System.out.println("A password é : " + s);
                            return s;
                        } else {
                            System.out.println(count++);;
                        }
                    }
                }
            }
        }
        return null;
    }


    ////////////NEW METHOD FOR OTIMIZATION////////////////////
    public ArrayList<char[]> plugboard(ArrayList<String>wordsWithSalt){
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
        /*System.out.println("Depois da plugboard: " + cont);
        System.out.println(plugedWords.toString());*/
        return plugedWords;

    }
    ////////////////////////////////////////

    /////////NEW METHOD FOR OTIMIZATION////////////////
    public ArrayList<String> putSalt(String word){
        String combinationFront;
        String combinationBack;
        ArrayList<String> wordWithSalt = new ArrayList<>();

            for (int i = 0; i < salt.length(); i++) {
                char char1 = salt.charAt(i);
                for (int j = 0; j < salt.length(); j++) {
                    char char2 = salt.charAt(j);
                    combinationFront = word + char1 + char2;
                    combinationBack = char1 + String.valueOf(char2) + word;

                    wordWithSalt.add(combinationFront);
                    wordWithSalt.add(combinationBack);
                }
            }
        return wordWithSalt;
    }
    ///////////////NEW METHOD FOR OTIMIZATION//////////////////

}
