package org.example;

import com.google.common.hash.Hashing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
       /*BreakingEnigma breakingEnigma = new BreakingEnigma();

        List<String> listOfStringEncrypet = new ArrayList<>();

        List<char[]> listForHash = breakingEnigma.enhancedCeaserCifer(breakingEnigma.plugboard(breakingEnigma.putSalt(breakingEnigma.insertWordsIntoArrayList())));

        for(char[] c : listForHash){
            String str = String.valueOf(c);
            String hash = String.valueOf(Hashing.sha512().hashString(str, StandardCharsets.UTF_8));
            listOfStringEncrypet.add(hash);
            System.out.println(hash);

            if(hash.equals("18aa4e563f44d86ad2019e17817af8d1d34f02263ec13d9c2536b6a2542d576e80bae1f4391acefa1ebca64365c05623537c5312f42c164b2bfa9af65e64cf2e")){
                System.out.println("A " + c + " é igual!");
            }
        }*/

        Otimização otimização = new Otimização();

        String password = otimização.methodForReadPath("18aa4e563f44d86ad2019e17817af8d1d34f02263ec13d9c2536b6a2542d576e80bae1f4391acefa1ebca64365c05623537c5312f42c164b2bfa9af65e64cf2e");
        System.out.println(password);


       /* Teste teste = new Teste();
        teste.teste();*/

    }
}