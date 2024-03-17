package com.feri.ita.paymentservice.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feri.ita.paymentservice.Models.ParkingTicket;

public interface ParkingTicketRepo extends JpaRepository<ParkingTicket, UUID> {

}
