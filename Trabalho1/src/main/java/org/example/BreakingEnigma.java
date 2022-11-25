package org.example;

import com.google.common.hash.Hashing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class BreakingEnigma {

    private static String salt = "ABCDEFGHIJKLM";
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static HashMap<Character, Character> hashMap;

    public static String methodForReadHash(String givenHash) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
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
        String hash = null;
        int count = 0;
        boolean valida = false;

        //LÊ O FICHEIRO E INSERE TODAS AS LETRAS NUM ARRAYLIST
        try (Scanner s = new Scanner(new FileReader("/Users/utilizador/Downloads/wordlist2.txt"))) {
            while (s.hasNext()) {
                words.add(s.nextLine());
            }
        }
            //LOOP PARA PERCORRER O ARRAYLIST COM TODAS AS PALAVRAS
            for(String s : words){
                long begin = System.currentTimeMillis();

                //LISTA DE PALAVRA COM TODAS AS COMBINAÇÕES DE SALT
                ArrayList<String> wordWithSalt = putSalt(s);

                //FOR LOOP PARA PERCORRER TODAS AS COMBINAÇÕES DE UMA PALAVRA COM O SALT
                for(String x : wordWithSalt){
                    //UMA COMBINAÇÃO DA PALAVRA PASSA NA PLUGBOARD
                    StringBuilder string = plugboard(x);

                    //FOR LOOP QUE FIXA A ROTAÇÃO E TESTA TODAS AS POSSIBILIDADES COM O INCREMENTO
                    for(int rot = 1; rot < alphabet.length(); rot++){
                        for(int inc = 1; inc < alphabet.length(); inc++){
                            //INICIALIZAÇÃO DA STRING QUE IRÁ SER FORMADA
                            String stringChanged = "";
                            //FOR LOOP PARA PERCORRER CADA CARACTER DA COMBINAÇÃO
                            for(int ch = 0; ch < string.length(); ch ++){
                                //CALCULOS DA ENHANCED CEASER CIFER
                                int resultInc = ch * inc;
                                int change = rot + resultInc;

                                //INDICE DO CARACTER EM QUESTÃO NO ALFABETO
                                int indexOfAlphabet = alphabet.indexOf(string.charAt(ch));
                                //INDICE DO CARACTER PARA QUAL DEVE SER ALTERADO
                                int index = (indexOfAlphabet + change) % 26;
                                //CARACTER NA POSIÇÃO DEFINIDA EM CIMA NO 'INDEX'
                                char charToChangeOnString = alphabet.charAt(index);
                                //CONSTRUÇÃO DA PALAVRA 'NOVA'
                                stringChanged += charToChangeOnString;
                            }

                            //PALAVRA FORMADA PASSA DE NOVO NA PLUGBOARD
                            String afterPlugString = String.valueOf(plugboard(stringChanged));

                            //GERAÇÃO DA HASH COM UTILIZAÇÃO DA BIBLIOTECA GUAVA
                            hash = String.valueOf(Hashing.sha512().hashString(afterPlugString, StandardCharsets.UTF_8));

                            //IF STATEMENT PARA VERIFICAR SE A HASH FORNECIDA PELO UTILIZADOR É A GERADA
                            //SE SIM, RETORNA A PASSOWORD, A ROTAÇÃO E O INCREMENTO
                            if(hash.equals(givenHash)){
                                System.out.println("A password é : " + s + " com R = " + rot + " e F = " + inc);
                                valida = true;
                                return s;
                            }
                        }
                    }
                }
                long end = System.currentTimeMillis();
                System.out.println(count ++ + ": For word: " + s + " in " + (end - begin) + " seconds");
            }
            return null;
    }

    public static String methodForReadPath(String givenHash, String path) throws FileNotFoundException{
        ArrayList<String> words = new ArrayList<>();
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
        String hash = null;
        int count = 0;
        boolean valida = false;;

        //LÊ O FICHEIRO E INSERE TODAS AS LETRAS NUM ARRAYLIST
        try (Scanner s = new Scanner(new FileReader(path))) {
            while (s.hasNext()) {
                words.add(s.nextLine());
            }
        }
        //LOOP PARA PERCORRER O ARRAYLIST COM TODAS AS PALAVRAS
        for(String s : words){
            long begin = System.currentTimeMillis();

            //LISTA DE PALAVRA COM TODAS AS COMBINAÇÕES DE SALT
            ArrayList<String> wordWithSalt = putSalt(s);

            //FOR LOOP PARA PERCORRER TODAS AS COMBINAÇÕES DE UMA PALAVRA COM O SALT
            for(String x : wordWithSalt){
                //UMA COMBINAÇÃO DA PALAVRA PASSA NA PLUGBOARD
                StringBuilder string = plugboard(x);

                //FOR LOOP QUE FIXA A ROTAÇÃO E TESTA TODAS AS POSSIBILIDADES COM O INCREMENTO
                for(int rot = 1; rot < alphabet.length(); rot++){
                    for(int inc = 1; inc < alphabet.length(); inc++){
                        //INICIALIZAÇÃO DA STRING QUE IRÁ SER FORMADA
                        String stringChanged = "";
                        //FOR LOOP PARA PERCORRER CADA CARACTER DA COMBINAÇÃO
                        for(int ch = 0; ch < string.length(); ch ++){
                            //CALCULOS DA ENHANCED CEASER CIFER
                            int resultInc = ch * inc;
                            int change = rot + resultInc;

                            //INDICE DO CARACTER EM QUESTÃO NO ALFABETO
                            int indexOfAlphabet = alphabet.indexOf(string.charAt(ch));
                            //INDICE DO CARACTER PARA QUAL DEVE SER ALTERADO
                            int index = (indexOfAlphabet + change) % 26;
                            //CARACTER NA POSIÇÃO DEFINIDA EM CIMA NO 'INDEX'
                            char charToChangeOnString = alphabet.charAt(index);
                            //CONSTRUÇÃO DA PALAVRA 'NOVA'
                            stringChanged += charToChangeOnString;
                        }

                        //PALAVRA FORMADA PASSA DE NOVO NA PLUGBOARD
                        String afterPlugString = String.valueOf(plugboard(stringChanged));

                        //GERAÇÃO DA HASH COM UTILIZAÇÃO DA BIBLIOTECA GUAVA
                        hash = String.valueOf(Hashing.sha512().hashString(afterPlugString, StandardCharsets.UTF_8));

                        //IF STATEMENT PARA VERIFICAR SE A HASH FORNECIDA PELO UTILIZADOR É A GERADA
                        //SE SIM, RETORNA A PASSOWORD, A ROTAÇÃO E O INCREMENTO
                        if(hash.equals(givenHash)){
                            System.out.println("A password é : " + s + " com R = " + rot + " e F = " + inc);
                            valida = true;
                            return s;
                        }
                    }
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(count ++ + ": For word: " + s + " in " + (end - begin) + " seconds");
        }
        return null;
    }

    /////////METODO DA PLUGBOARD///////////////
    private static StringBuilder plugboard(String word){
        char[] charArray = word.toCharArray();

        for(int i = 0; i < word.length(); i++){
            for(Character c : hashMap.keySet()){
                if(c.charValue() == word.charAt(i)){
                    Array.setChar(charArray,i,hashMap.get(c));
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(charArray));

        return stringBuilder;
    }

    /////////METODO SALT////////////////
    private static ArrayList<String> putSalt(String word){
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
                if(!combinationFront.equals(combinationBack)){
                    wordWithSalt.add(combinationBack);
                }
            }
        }
        return wordWithSalt;
    }
}



