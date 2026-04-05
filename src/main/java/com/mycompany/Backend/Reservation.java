package com.mycompany.Backend;

import java.time.LocalDate;

public class Reservation {
    private String reservationId;   // unique identifier
    private String guestId;         // guest identifier
    private String roomNumber;      // room number (String)
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(String reservationId, String guestId, String roomNumber,
                       LocalDate checkIn, LocalDate checkOut) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getReservationId() { return reservationId; }
    public String getGuestId() { return guestId; }
    public String getRoomNumber() { return roomNumber; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }

    @Override
    public String toString() {
        return reservationId + "|" + guestId + "|" + roomNumber + "|" + checkIn + "|" + checkOut;
    }
}
