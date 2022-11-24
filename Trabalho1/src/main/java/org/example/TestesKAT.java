package org.example;


import java.lang.reflect.Array;

import java.util.HashMap;

public class TestesKAT {

    private final String salt = "ABCDEFGHIJKLM";
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static HashMap<Character, Character> hashmapTeste;

    public void teste() {
        String word = "EVAUICMRHFWPT";

        char[] array = word.toCharArray();
        hashmapTeste = new HashMap<Character, Character>();
        //TESTE XOD
        /*hashmapTeste.put('D', 'R');
        hashmapTeste.put('L', 'H');
        hashmapTeste.put('T', 'N');
        hashmapTeste.put('M', 'E');*/

        //TESTE EVAUICMRHFWPT
        hashmapTeste.put('A', 'B');
        hashmapTeste.put('C', 'O');

        for (int i = 0; i < array.length; i++) {
            for (Character c : hashmapTeste.keySet()) {
                if (c.charValue() == array[i]) {
                    Array.setChar(array, i, hashmapTeste.get(c));
                }
            }
        }
        System.out.println(array);
        StringBuilder str = new StringBuilder(String.valueOf(array));

        String stringChanged = "";
        for (int ch = 0; ch < str.length(); ch++) {
            int resultInc = ch * 25;
            int change = 22 + resultInc;

            int indexOfAlphabet = alphabet.indexOf(str.charAt(ch));
            //saber o indice do caractere para que deve ser alterado o caracter
            int index = (indexOfAlphabet + change) % 26;
            //posicao do caractere no alfabeto para ser colocado na string encriptada
            char charToChangeOnString = alphabet.charAt(index);
            //fazer a string
            stringChanged += charToChangeOnString;
        }

        char[] arrayChanged = stringChanged.toCharArray();

        for (int i = 0; i < arrayChanged.length; i++) {
            for (Character c : hashmapTeste.keySet()) {
                if (c.charValue() == arrayChanged[i]) {
                    Array.setChar(arrayChanged, i, hashmapTeste.get(c));
                }
            }
        }
        System.out.println(arrayChanged);
    }
}
