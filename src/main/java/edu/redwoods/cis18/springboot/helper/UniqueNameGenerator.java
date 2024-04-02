package edu.redwoods.cis18.springboot.helper;

import java.util.HashSet;
import java.util.Random;

public class UniqueNameGenerator {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateUniqueName(int length) {
        Random random = new Random();
        HashSet<String> generatedNames = new HashSet<>();
        String name;

        do {
            StringBuilder builder = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                builder.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
            }
            name = builder.toString();
        } while (generatedNames.contains(name));

        generatedNames.add(name);
        return name;
    }
}
