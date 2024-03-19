package com.feri.ita.paymentservice.Models;

public class ParkingTicketStatus
{
    private Boolean isPaid;

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public ParkingTicketStatus() {
        super();
    }

    public ParkingTicketStatus(Boolean isPaid){
        this();
        this.isPaid = isPaid;
    }
}
