package com.javarush.cryptoanalyer;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List<Character> ALPHABET = Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static String stringCode(String text, int number) { // построчная кодировка
        if (number < 0)
            return "Wrong key";
        int key = number % ALPHABET.size(); // получение ключа
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (ALPHABET.contains(symbol)) {
                int oldIndex = ALPHABET.indexOf(symbol); // поиск индекса в алфавите
                int newIndex = oldIndex + key; // новый индекс
                if (newIndex >= ALPHABET.size()) {
                    newIndex -= ALPHABET.size(); // сделать круг по алфавиту
                }
                char newSymbol = ALPHABET.get(newIndex); // новый символ
                sb.append(newSymbol);
            }
        }
        return sb.toString();
    }

    public static String stringDecode(String text, int number) { // построчная декодировка
        if (number < 0)
            return "Wrong key";
        int key = number % ALPHABET.size(); // получение ключа
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (ALPHABET.contains(symbol)) {
                int oldIndex = ALPHABET.indexOf(symbol); // поиск индекса в алфавите
                int newIndex = oldIndex - key;
                if (newIndex < 0) {
                    newIndex += ALPHABET.size(); // сделать круг по алфавиту
                }
                char newSymbol = ALPHABET.get(newIndex);
                sb.append(newSymbol);
            }
        }
        return sb.toString();
    }

    public static void сode(String path, int key) { // кодировка файла
        try (FileInputStream fis = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("codedText.txt")))) {

            while (reader.ready()) {
                String s = reader.readLine();
                writer.write(stringCode(s, key));
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deCode(String path, int key) { // декодировка файла
        try (FileInputStream fis = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("decodedText.txt")))) {

            while (reader.ready()) {
                String s = reader.readLine();
                writer.write(stringDecode(s, key));
                writer.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("CryptoAnalyzer");
    }
}
