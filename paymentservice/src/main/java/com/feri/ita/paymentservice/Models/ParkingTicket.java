package com.feri.ita.paymentservice.Models;

import java.time.LocalDateTime;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;

@Entity
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String licensePlate;

    @Column
    private LocalDateTime entryTimestamp;

    @Column
    @Nullable
    private LocalDateTime paidTimestamp;

    @Column
    @Nullable
    private LocalDateTime leavingTimestamp;

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
    public LocalDateTime getEntryTimestamp() {
        return entryTimestamp;
    }
    public void setEntryTimestamp(LocalDateTime entryTimestamp) {
        this.entryTimestamp = entryTimestamp;
    }
    public @Nullable LocalDateTime getPaidTimestamp() {
        return paidTimestamp;
    }
    public void setPaidTimestamp(LocalDateTime paidTimestamp) {
        this.paidTimestamp = paidTimestamp;
    }
    public @Nullable LocalDateTime getLeavingTimestamp() {
        return leavingTimestamp;
    }
    public void setLeavingTimestamp(LocalDateTime leavingTimestamp) {
        this.leavingTimestamp = leavingTimestamp;
    }

    public ParkingTicket() {
        super();
    }

    public ParkingTicket(
        String licensePlate,
        LocalDateTime entryTimestamp) {
        this();
        this.licensePlate = licensePlate;
        this.entryTimestamp = entryTimestamp;
    }
}
