package org.example;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean validateHash = false;
        boolean validatePlugboard = false;
        boolean validatePath = false;

        String hash = null;
        String plugboard = null;
        String path = null;

        System.out.println("Hello! Welcome to the application it will break your hash and find your password... or even break your mind...\n");

        do {
            System.out.println("Insert the hash: ");
            hash = args[0];
            //hash = "18aa4e563f44d86ad2019e17817af8d1d34f02263ec13d9c2536b6a2542d576e80bae1f4391acefa1ebca64365c05623537c5312f42c164b2bfa9af65e64cf2e";
            if (hash.length() == 128) {
                if (hash.matches("[a-z0-9]+")) {
                    validateHash = true;
                } else {
                    System.out.println("need to insert a hash with only letters and numbers.");
                }
            } else {
                System.out.println("hash must contain 128 characters.");
            }
        } while (validateHash == false);


        String newPlugboard = null;
        do {
            System.out.println("Insert the plugboard: ");
            plugboard = args[1];
            //plugboard = "{'T': 'D','A': 'J','I': 'Q','U': 'C','O': 'X','N': 'P','Y': 'B','E': 'H','G': 'K','L': 'S'}";
            if(plugboard.matches("[{'A-Z': 'A-Z',}]+")){
                newPlugboard = plugboard.replaceAll("[^A-Z]", "");
                validatePlugboard = true;
            } else {
                System.out.println("plugboard should be insert like this: {'A': 'B','Z': 'X'}");
            }
        } while (validatePlugboard == false);


        do {
            System.out.println("Insert the path for a wordlist: ");
            path = args[2];
            //path = "/Users/utilizador/Downloads/wordlist2.txt";
            File file = new File(path);
            if (file.exists()) {
                validatePath = true;
            } else {
                System.out.println("File not found.");
            }
        } while (validatePath == false);

        if(validateHash == true && validatePlugboard == true && validatePath == true){
            BreakingEnigma.enhancedCeaser(hash,newPlugboard,path);
        }

    }

}
