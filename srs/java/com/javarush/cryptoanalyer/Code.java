package com.javarush.cryptoanalyer;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.Scanner;

public class Code extends AbstractCode {

    public void encode(String path, int key) { // кодировка файла
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader);
             BufferedWriter writer = new BufferedWriter(new FileWriter("encodedText.txt"))) {

            while (reader.ready()) {
                String stringOfText = reader.readLine();
                writer.write(stringEncode(stringOfText, key));
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("File is not found" + e.getMessage());
        }
    }

    public void decode(String path, int key) { // декодировка файла
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader);
             BufferedWriter writer = new BufferedWriter(new FileWriter("decodedText.txt"))) {

            while (reader.ready()) {
                String stringOfText = reader.readLine();
                writer.write(stringDecode(stringOfText, key));
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("File is not found" + e.getMessage());
        }
    }

    public void bruteForce(String path) { // взлом
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader);
             BufferedWriter writer = new BufferedWriter(new FileWriter("bruteForceText.txt"))) {
            reader.mark(1);
            int key = 0;
            Scanner scanner = new Scanner(System.in);
            String stringOfText;
            while (true) {
                stringOfText = reader.readLine();
                System.out.println(stringDecode(stringOfText, key));
                System.out.println("a meaningful expression? / 1 - \"yes\", 2 - \"no\""); // осмысленно?
                int answer = scanner.nextInt();
                if (answer == 1) {
                    reader.reset();
                    while (reader.ready()) {
                        stringOfText = reader.readLine();
                        writer.write(stringDecode(stringOfText, key));
                        writer.write("\n");
                    }
                    break;
                } else if (answer == 2) {
                    key++;
                    reader.reset();
                }
            }
        } catch (IOException e) {
            System.out.println("File is not found" + e.getMessage());
        }
    }
}
