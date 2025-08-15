package com.inventory.inventorySystem.utils;

public class StringNormalizer{

    public static String toTitleCase(String text) {
        if (text == null || text.isBlank()) {
            return text;
        }
        var normalizedText = new StringBuilder();
        boolean nextTitleCase = true;

        for (char character : text.trim().toLowerCase().toCharArray()) {
            if (Character.isSpaceChar(character)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                character = Character.toTitleCase(character);
                nextTitleCase = false;
            }
            normalizedText.append(character);
        }
        return normalizedText.toString();
    }
}
