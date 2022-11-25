package org.example;

import java.io.*;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Scanner read = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        int option = -1;
        boolean validate = false;
        Matcher matches = null;

        System.out.println("Hello! Welcome to the apllication it while break your hash... or even break your mind...");

        do {
            System.out.println("----------MENU---------");
            System.out.println("1 - Give me the hash");
            System.out.println("2 - Path of wordlist file");
            System.out.println("3 - Plugboard");
            System.out.println("Introduza a opção pretendida: ");

            option = read.nextInt();

                switch(option){
                    case 1:
                        do {
                            System.out.print("Insert the hash: ");
                            String hash = read.next();
                            if (hash.length() == 128) {
                                BreakingEnigma.methodForReadHash(hash);
                                validate = true;
                            } else {
                                System.out.println("Insira um hash com 128 caracteres.\n");
                                break;
                            }
                        } while (validate == false);
                        break;

                    case 2:
                        FileSystem fs = FileSystems.getDefault();
                        do{
                            FileReader fileReader;
                            System.out.print("Insert the path for the wordlist file: ");
                            String path = read.next();
                            File file = new File(path);
                            System.out.println(path);

                            System.out.print("Insert the hash: ");
                            String hash = read.next();

                            if(file.exists() && hash.length() == 128){
                                fileReader = new FileReader(file);
                                //String pathInString = String.valueOf(fileReader);
                                BreakingEnigma.methodForReadPath(hash,path);
                                validate = true;
                            }
                        } while (validate == false);
                        break;

                    case 3:
                        validate = true;
                        break;
                }
        } while(validate == false);

    }
}
