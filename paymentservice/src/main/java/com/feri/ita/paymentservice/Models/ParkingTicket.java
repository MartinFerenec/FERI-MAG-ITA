package com.feri.ita.paymentservice.Models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String licensePlate;

    @Column
    private Date entryTimestamp;

    @Column
    private Date paidTimestamp;

    @Column
    private Date leavingTimestamp;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public Date getEntryTimestamp() {
        return entryTimestamp;
    }
    public void setEntryTimestamp(Date entryTimestamp) {
        this.entryTimestamp = entryTimestamp;
    }
    public Date getPaidTimestamp() {
        return paidTimestamp;
    }
    public void setPaidTimestamp(Date paidTimestamp) {
        this.paidTimestamp = paidTimestamp;
    }
    public Date getLeavingTimestamp() {
        return leavingTimestamp;
    }
    public void setLeavingTimestamp(Date leavingTimestamp) {
        this.leavingTimestamp = leavingTimestamp;
    }

    public ParkingTicket(
        String licensePlate,
        Date entryTimestamp) {
        this.licensePlate = licensePlate;
        this.entryTimestamp = entryTimestamp;
    }
}
