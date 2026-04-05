package com.mycompany.Backend;

import java.io.*;
import java.util.*;

public class RoomService {
    private static final String FILE = "rooms.txt";

    // Add a new room
    public void addRoom(Room r) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(r.getNumber() + "|" + r.getType() + "|" + r.getPrice() + "|" + r.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    try {
                        String number = parts[0];
                        String type = parts[1];
                        double price = Double.parseDouble(parts[2]);
                        String status = parts[3];
                        rooms.add(new Room(number, type, price, status));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid price format in record: " + line);
                    }
                } else {
                    System.err.println("Skipping invalid room record: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Delete a room by number
    public void deleteRoom(String number) {
        List<Room> rooms = getAllRooms();
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Room r : rooms) {
                if (!r.getNumber().equals(number)) {
                    pw.println(r.getNumber() + "|" + r.getType() + "|" + r.getPrice() + "|" + r.getStatus());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update room status (availability)
    public void updateRoomStatus(String number, String newStatus) {
        List<Room> rooms = getAllRooms();
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Room r : rooms) {
                if (r.getNumber().equals(number)) {
                    r.setStatus(newStatus);
                }
                pw.println(r.getNumber() + "|" + r.getType() + "|" + r.getPrice() + "|" + r.getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Find a room by number
    public Room getRoomByNumber(String number) {
        for (Room r : getAllRooms()) {
            if (r.getNumber().equals(number)) {
                return r;
            }
        }
        return null;
    }
}