package feri.ita.Models;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "parking_ticket")
public class ParkingTicket extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "entry_timestamp")
    private LocalDateTime entryTimestamp;

    @Nullable
    @Column(name = "paid_timestamp")
    private LocalDateTime paidTimestamp;

    @Nullable
    @Column(name = "leaving_timestamp")
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
