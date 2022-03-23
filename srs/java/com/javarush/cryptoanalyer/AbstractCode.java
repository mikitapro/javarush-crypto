package com.javarush.cryptoanalyer;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractCode {

    private static final List<Character> ALPHABET = Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static final String WRONG_KEY = "Wrong_key";

    public static String stringEncode(String text, int number) { // построчная кодировка
        if (number < 0)
            return WRONG_KEY;
        int key = number % ALPHABET.size(); // получение ключа
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (ALPHABET.contains(symbol)) {
                int oldIndex = ALPHABET.indexOf(symbol);
                int newIndex = oldIndex + key;
                if (newIndex >= ALPHABET.size()) {
                    newIndex -= ALPHABET.size();
                }
                char newSymbol = ALPHABET.get(newIndex);
                encodedText.append(newSymbol);
            }
        }
        return encodedText.toString();
    }

    public static String stringDecode(String text, int number) { // построчная декодировка
        if (number < 0)
            return "Wrong key";
        int key = number % ALPHABET.size(); // получение ключа
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (ALPHABET.contains(symbol)) {
                int oldIndex = ALPHABET.indexOf(symbol);
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
}
