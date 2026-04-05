package com.mycompany.Backend;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GuestService {
    private static final String FILE = "guests.txt";

    public void addGuest(Guest g) {
    String record = g.getId() + "|" + g.getName() + "|" + g.getPhone() + "|" + g.getEmail();
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("guests.txt", true))) {
        bw.write(record);
        bw.newLine(); // ensures each guest is on its own line
        System.out.println("Record written: " + record);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public List<Guest> getAllGuests() {
    List<Guest> guests = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("guests.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                guests.add(new Guest(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    parts[3]
                ));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return guests;
}


    public void updateGuest(int id, Guest updated) {
        List<String> lines = FileHandler.readAll(FILE);
        List<String> newLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split("\\|");
            try {
                int currentId = Integer.parseInt(parts[0]);
                if (currentId == id) {
                    newLines.add(updated.getId() + "|" + updated.getName() + "|" + updated.getPhone() + "|" + updated.getEmail());
                    found = true;
                } else {
                    newLines.add(line);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid ID format in record: " + line);
                newLines.add(line); // keep original line
            }
        }

        if (!found) {
            System.err.println("Guest with ID " + id + " not found.");
        }

        FileHandler.overwrite(FILE, newLines);
    }

    public void deleteGuest(int id) {
        List<String> lines = FileHandler.readAll(FILE);
        List<String> newLines = new ArrayList<>();
        boolean deleted = false;

        for (String line : lines) {
            String[] parts = line.split("\\|");
            try {
                int currentId = Integer.parseInt(parts[0]);
                if (currentId != id) {
                    newLines.add(line);
                } else {
                    deleted = true;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid ID format in record: " + line);
                newLines.add(line); // keep original line
            }
        }

        if (!deleted) {
            System.err.println("Guest with ID " + id + " not found.");
        }

        FileHandler.overwrite(FILE, newLines);
    }
}

