package com.javarush.cryptoanalyer;

import java.io.*;

public class Main {

    private static final String PATH = "Enter path to file";

    private static final String KEY = "Enter key";

    private static final String WRONG_NUMBER = "Wrong number";

    private static final Code code = new Code();

    public static void main(String[] args) {

        System.out.println("CryptoAnalyzer");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Menu - 1 \nEncode - 2 \nDecode - 3 \nBrute - 4 \nExit - 5");
                int indexMenu = Integer.parseInt(reader.readLine());
                String path;
                int key;
                switch (indexMenu) {
                    case 1:
                        break;
                    case 2:
                        System.out.println(PATH);
                        path = reader.readLine();
                        System.out.println(KEY);
                        key = Integer.parseInt(reader.readLine());
                        code.encode(path, key);
                        break;
                    case 3:
                        System.out.println(PATH);
                        path = reader.readLine();
                        System.out.println(KEY);
                        key = Integer.parseInt(reader.readLine());
                        code.decode(path, key);
                        break;
                    case 4:
                        System.out.println(PATH);
                        path = reader.readLine();
                        code.bruteForce(path);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println(WRONG_NUMBER);
                }
            }
        } catch (IOException e) {
            System.out.println("Input / Output error" + e.getMessage());
        }
    }
}
