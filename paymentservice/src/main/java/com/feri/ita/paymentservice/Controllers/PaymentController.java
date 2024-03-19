package com.feri.ita.paymentservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Models.ParkingTicketStatus;
import com.feri.ita.paymentservice.Services.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/parkingTicket/{id}")
    public ResponseEntity<ParkingTicket> getParkingTicket(@PathVariable long id){
        return paymentService.getParkingTicket(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/isTicketPaid/{id}")
    public ResponseEntity<ParkingTicketStatus> isTicketPaid(@PathVariable long id){
       return paymentService.isTicketPaid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/markAsPaid/{id}") 
    public ResponseEntity<ParkingTicket> markAsPaid(@PathVariable long id) {
        return paymentService.markAsPaid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
