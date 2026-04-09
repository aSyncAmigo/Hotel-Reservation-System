
package com.mycompany.Backend;
import java.time.LocalDate;

public class Payment {
    private int id;
    private int reservationId;   
    private double amount;
    private String method;       
    private LocalDate date;

    
    public Payment(int id, int reservationId, double amount, String method, LocalDate date) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.method = method;
        this.date = date;
    }

   
    public int getId() { return id; }
    public int getReservationId() { return reservationId; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public LocalDate getDate() { return date; }

    public void setId(int id) { this.id = id; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setMethod(String method) { this.method = method; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "Payment [ID=" + id + ", ReservationID=" + reservationId +
               ", Amount=" + amount + ", Method=" + method + ", Date=" + date + "]";
    }
}
