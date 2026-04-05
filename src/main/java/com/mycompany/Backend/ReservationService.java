package com.mycompany.Backend;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {
    private static final String FILE = "reservations.txt";
    private final RoomService roomService = new RoomService();

    // Add reservation
    public void addReservation(Reservation r) {
        Room room = roomService.getRoomByNumber(r.getRoomNumber());
        if (room == null) {
            System.err.println("Room not found: " + r.getRoomNumber());
            return;
        }
        if (!room.getStatus().equals("Available")) {
            System.err.println("Room not available: " + r.getRoomNumber());
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(r.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomService.updateRoomStatus(r.getRoomNumber(), "Booked");
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String reservationId = parts[0];
                    String guestId = parts[1];
                    String roomNumber = parts[2];
                    LocalDate checkIn = LocalDate.parse(parts[3]);
                    LocalDate checkOut = LocalDate.parse(parts[4]);

                    reservations.add(new Reservation(reservationId, guestId, roomNumber, checkIn, checkOut));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Delete reservation by ID
    public void deleteReservation(String reservationId) {
        List<Reservation> reservations = getAllReservations();
        Reservation target = null;

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Reservation r : reservations) {
                if (!r.getReservationId().equals(reservationId)) {
                    pw.println(r.toString());
                } else {
                    target = r;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (target != null) {
            roomService.updateRoomStatus(target.getRoomNumber(), "Available");
        }
    }
}
