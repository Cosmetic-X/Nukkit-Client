/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static String file_get_contents(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder builder = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        return builder.toString();
    }

    public static String encodeSkinData(byte[] skinData){
        return ""; // TODO: this should return png file contents
    }

    public static byte[] decodeSkinData(String raw){
        return new byte[0]; // TODO: this should use png file contents and convert it to byte array
    }
}
