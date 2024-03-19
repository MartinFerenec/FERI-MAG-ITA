package com.feri.ita.paymentservice.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Models.ParkingTicketStatus;
import com.feri.ita.paymentservice.Repo.ParkingTicketRepo;

@Service
public class PaymentService {

    @Autowired
    private ParkingTicketRepo parkingTicketRepo;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public Optional<ParkingTicket> getParkingTicket(long id){
        logger.info("Fetching parking ticket. Id: {}", id);
        return parkingTicketRepo.findById(id);
    }

    public Optional<ParkingTicketStatus> isTicketPaid(long id){
        Optional<ParkingTicket> parkingTicket = parkingTicketRepo.findById(id);
        if (!parkingTicket.isPresent()){
            logger.error("Parking ticket not found. Id: {}", id);
            return Optional.empty();
        }

        logger.info("Returning parking ticket paid status. Id: {}", id);
        return Optional.of(new ParkingTicketStatus(parkingTicket.get().getPaidTimestamp() != null));
    }

    public Optional<ParkingTicket> markAsPaid(long id) {
        Optional<ParkingTicket> parkingTicket = parkingTicketRepo.findById(id);
        if (!parkingTicket.isPresent()){
            logger.error("Parking ticket not found. Id: {}", id);
            return Optional.empty();
        }

        if (parkingTicket.get().getPaidTimestamp() != null){
            logger.error("Parking ticket already paid. Id: {}", id);
            return Optional.empty();
        }

        logger.info("Marking parking ticket as paid. Id: {}", parkingTicket.get().getId());
        parkingTicket.get().setPaidTimestamp(LocalDateTime.now());
        return Optional.of(parkingTicketRepo.save(parkingTicket.get()));
    }
}