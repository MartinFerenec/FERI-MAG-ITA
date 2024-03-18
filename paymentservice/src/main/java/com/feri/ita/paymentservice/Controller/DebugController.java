package com.feri.ita.paymentservice.Controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Repo.ParkingTicketRepo;

@RestController()
public class DebugController {

    @Autowired
    ParkingTicketRepo parkingTicketRepo;

    @GetMapping("/debug/newDummyParkingTicket")
    public ParkingTicket dummyParkingTicket(){
        ParkingTicket ticket = new ParkingTicket("MB XX-XXX", LocalDateTime.now());

        return parkingTicketRepo.save(ticket);
    }
}
