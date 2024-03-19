package com.feri.ita.paymentservice.Controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Repo.ParkingTicketRepo;

@RestController()
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    ParkingTicketRepo parkingTicketRepo;

    @GetMapping("/newDummyParkingTicket")
    public ParkingTicket dummyParkingTicket(){
        ParkingTicket ticket = new ParkingTicket("MB XX-XXX", LocalDateTime.now());

        return parkingTicketRepo.save(ticket);
    }
}
