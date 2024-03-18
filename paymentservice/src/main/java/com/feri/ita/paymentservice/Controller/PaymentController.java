package com.feri.ita.paymentservice.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Repo.ParkingTicketRepo;

@RestController
public class PaymentController {

    @Autowired
    private ParkingTicketRepo parkingTicketRepo;

    @GetMapping("/parkingTicket/{id}")
    public Optional<ParkingTicket> getParkingTicket(@PathVariable long id){
        return parkingTicketRepo.findById(id);
    }

    @PatchMapping("/markAsPaid/{id}") 
    public ParkingTicket markAsPaid(@PathVariable long id) {
        Optional<ParkingTicket> parkingTicket = parkingTicketRepo.findById(id);
        if (!parkingTicket.isPresent()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Parking ticket not found.");
        }

        if (parkingTicket.get().getPaidTimestamp() != null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Parking ticket already paid.");
        }

        parkingTicket.get().setPaidTimestamp(LocalDateTime.now());
        return parkingTicketRepo.save(parkingTicket.get());
    }
}
