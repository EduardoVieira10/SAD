package org.example;

import com.google.common.hash.Hashing;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BreakingEnigma {

    private static String salt = "ABCDEFGHIJKLM";
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static HashMap<Character, Character> hashMap;

    /**
     * Métodos que vai receber 3 parâmetros e valida-los
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{

        boolean validateHash = false;
        boolean validatePlugboard = false;
        boolean validatePath = false;

        String hash = null;
        String plugboard = null;
        String path = null;

        System.out.println("\nHello! Welcome to the application it will break your hash and find your password... or even break your mind...\n");

            hash = args[0];

            if (hash.length() == 128) {
                if (hash.matches("[a-f0-9]+$")) {
                    validateHash = true;
                } else {
                    System.out.println("need to insert a hash with only letters and numbers.");
                }
            } else {
                System.out.println("hash must contain 128 characters.");
            }


            String newPlugboard = null;

            plugboard = args[1];
            if(plugboard.matches("[{'A-Z': 'A-Z',}]+")){
                newPlugboard = plugboard.replaceAll("[^A-Z]", "");
                validatePlugboard = true;
            } else {
                System.out.println("plugboard should be insert like this: {'A': 'B','Z': 'X'}.");
            }

            path = args[2];
            File file = new File(path);
            if (file.exists()) {
                validatePath = true;
            } else {
                System.out.println("File not found.");
            }

            if(validateHash == true && validatePlugboard == true && validatePath == true){
                enhancedCeaser(hash,newPlugboard,path);
            }
        }


    /**
     * Método que implementa a Cifra de Cesar melhorada
     * @param givenHash
     * @param plugboard
     * @param path
     * @return password
     * @throws FileNotFoundException
     */
    private static String enhancedCeaser(String givenHash, String plugboard, String path) throws FileNotFoundException{
        ArrayList<String> words = new ArrayList<>();

        hashMap = new HashMap<>();

        //MÉTODO PARA INSERIR PLUGBOARD NO HASHMAP
        for(int k = 0; k < plugboard.length(); k++){
            int module = k % 2;

            if(module == 0){
                hashMap.put(plugboard.charAt(k), plugboard.charAt(k+1));
            }
        }

        String hash = null;
        int count = 0;

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
                String string = plugboard(x);

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
                        String afterPlugString = plugboard(stringChanged);

                        //GERAÇÃO DA HASH COM UTILIZAÇÃO DA BIBLIOTECA GUAVA
                        hash = String.valueOf(Hashing.sha512().hashString(afterPlugString, StandardCharsets.UTF_8));

                        //IF STATEMENT PARA VERIFICAR SE A HASH FORNECIDA PELO UTILIZADOR É A GERADA
                        //SE SIM, RETORNA A PASSOWORD, A ROTAÇÃO E O INCREMENTO
                        if(hash.equals(givenHash)){
                            System.out.println("A password é : " + s + " com R = " + rot + " e F = " + inc);
                            return s;
                        }
                    }
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(count ++ + ": For word: " + s + " in " + (end - begin) + " miliseconds");
        }
        return null;
    }

    /**
     * Método para trocar de caracteres na plugboard
     * @param word
     * @return string switched
     */
    private static String plugboard(String word){
        char[] charArray = word.toCharArray();

        for(int i = 0; i < word.length(); i++){
            for(Character c : hashMap.keySet()){
                if(c.charValue() == word.charAt(i)){
                    Array.setChar(charArray,i,hashMap.get(c));
                    break;
                }
            }
        }
        String string = String.valueOf(charArray);

        return string;
    }

    /**
     * Método para retornar todas as combinações de uma palavra com o salt
     * @param word
     * @return lista com todas as combinações possíveis
     */
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

                //VERIFICA SE AS DUAS PALAVRAS FORMADAS SÃO IGUAIS
                if(!combinationFront.equals(combinationBack)){
                    wordWithSalt.add(combinationBack);
                }
            }
        }
        return wordWithSalt;
    }

}
