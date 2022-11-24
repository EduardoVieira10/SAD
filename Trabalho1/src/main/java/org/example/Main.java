package org.example;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        /*System.out.println("Hello! Welcome to the apllication it while break your hash... or even break your mind...*//*");

        int option = 0;
        try{
            while(option != 1 || option != 2 || option != 3){

                System.out.println("1 - Give me the hash");
                System.out.println("2 - Path of wordlist file");
                System.out.println("3 - Plugboard");            
            }

        } catch (NumberFormatException e){
            System.out.println("Introduza apenas algarimos!");
        }

        switch (option){
            case 1:

        }*/

        BreakingEnigma breakingEnigma = new BreakingEnigma();

        String password = breakingEnigma.methodForReadPath("18aa4e563f44d86ad2019e17817af8d1d34f02263ec13d9c2536b6a2542d576e80bae1f4391acefa1ebca64365c05623537c5312f42c164b2bfa9af65e64cf2e");
        System.out.println(password);


       /* TestesKAT teste = new TestesKAT();
        teste.teste();*/

    }
}