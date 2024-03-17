package com.feri.ita.paymentservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Repo.ParkingTicketRepo;

@RestController
public class ApiControllers {

    @Autowired
    private ParkingTicketRepo parkingTicketRepo;

    @GetMapping("/")
    public String index() {
        return "Hello world";
    }

    @GetMapping("/parkingTickets")
    public List<ParkingTicket> getParkingTickets() {
        return parkingTicketRepo.findAll();
    }
}
