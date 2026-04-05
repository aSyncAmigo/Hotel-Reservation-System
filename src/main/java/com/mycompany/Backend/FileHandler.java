package com.mycompany.Backend;

import java.io.*;
import java.util.*;

public class FileHandler {

    public static void writeLine(String filename, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file '" + filename + "': " + e.getMessage());
        }
    }

    public static List<String> readAll(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename + ". A new file will be created when writing.");
        } catch (IOException e) {
            System.err.println("Error reading file '" + filename + "': " + e.getMessage());
        }
        return lines;
    }

    public static void overwrite(String filename, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error overwriting file '" + filename + "': " + e.getMessage());
        }
    }
}
