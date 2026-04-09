package com.mycompany.Backend;

public class Room {
    private String number;   
    private String type;     
    private double price;    
    private String status;   

    public Room(String number, String type, double price, String status) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public String getNumber() { return number; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setNumber(String number) { this.number = number; }
    public void setType(String type) { this.type = type; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Room [Number=" + number + ", Type=" + type +
               ", Price=" + price + ", Status=" + status + "]";
    }
}
