
package com.mycompany.Backend;

import java.time.LocalDate;
import java.util.*;

public class PaymentService {
    private static final String FILE = "payments.txt";

    // Add a payment
    public void addPayment(Payment p) {
        String record = p.getId() + "|" + p.getReservationId() + "|" +
                        p.getAmount() + "|" + p.getMethod() + "|" + p.getDate();
        FileHandler.writeLine(FILE, record);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        List<String> lines = FileHandler.readAll(FILE);

        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 5) {
                try {
                    int id = Integer.parseInt(parts[0]);
                    int reservationId = Integer.parseInt(parts[1]);
                    double amount = Double.parseDouble(parts[2]);
                    String method = parts[3];
                    LocalDate date = LocalDate.parse(parts[4]);
                    payments.add(new Payment(id, reservationId, amount, method, date));
                } catch (Exception e) {
                    System.err.println("Invalid payment record: " + line);
                }
            } else {
                System.err.println("Skipping invalid payment record: " + line);
            }
        }
        return payments;
    }

    // Delete a payment
    public void deletePayment(int id) {
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
                System.err.println("Invalid ID format in payment record: " + line);
                newLines.add(line);
            }
        }

        if (!deleted) {
            System.err.println("Payment with ID " + id + " not found.");
        }

        FileHandler.overwrite(FILE, newLines);
    }
}
