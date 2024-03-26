package feri.ita.Models;

public class ParkingTicketStatus
{
    private Boolean isPaid;

    private double dueAmount;

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public ParkingTicketStatus() {
        super();

        this.isPaid = false;
        this.dueAmount = 0;
    }

    public ParkingTicketStatus(Boolean isPaid){
        this();
        this.isPaid = isPaid;
    }

    public ParkingTicketStatus(double dueAmount) {
        this();
        this.dueAmount = dueAmount;
    }
}
