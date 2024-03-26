package feri.ita;

import java.time.LocalDateTime;
import org.jboss.logging.Logger;

import feri.ita.Models.ParkingTicket;
import feri.ita.Repository.ParkingTicketRepository;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@GrpcService
public class TicketMachineServiceGrpcService implements TicketMachineServiceGrpc {

    private static final Logger logger = 
    Logger.getLogger(TicketMachineServiceGrpcService.class);
    
    @Inject
    ParkingTicketRepository parkingTicketRepo;

    @Override
    @Blocking
    @Transactional
    public Uni<TicketReply> issueTicket(TicketRequest request) {

        ParkingTicket ticket = new ParkingTicket(
            request.getLicensePlate(), 
            LocalDateTime.now());
        parkingTicketRepo.persistAndFlush(ticket);

        logger.info("Issuing new parking ticket. Id: " + ticket.getId());
        
        return Uni.createFrom().item(ticket)
                .map(x -> TicketReply.newBuilder()
                    .setId(ticket.getId())
                    .setLicensePlate(ticket.getLicensePlate())
                    .setEntryTimestamp(ticket.getEntryTimestamp().toString()).build());
    }

    @Override
    @Blocking
    public Uni<TicketPaidReply> checkIfTicketIsPaid(IdRequest request){
        ParkingTicket ticket = parkingTicketRepo.findById((long)request.getId());
        
        if (ticket.equals(null))
        {
            logger.error("Parking ticket not found. Id: " + request.getId());
            return Uni.createFrom().item(ticket)
                .map(x -> TicketPaidReply.newBuilder().setPaid(false).build());
        }

        logger.info("Returning parking ticket paid info. Id: " + ticket.getId());

        return Uni.createFrom().item(ticket)
        .map(x -> TicketPaidReply.newBuilder()
            .setPaid(ticket.getPaidTimestamp() != null).build());
    }

    @Override
    @Blocking
    @Transactional
    public Uni<SuccessReply> setLeavingTimestamp(IdRequest request){

        ParkingTicket ticket = parkingTicketRepo.findById((long)request.getId());;
        
        if (ticket == null)
        {
            logger.error("Ticket not found. Id: " + ticket.getId());
            Uni.createFrom().nullItem()
                .map(x -> SuccessReply.newBuilder()
                    .setSuccess(false).build());
        }

        ticket.setLeavingTimestamp(LocalDateTime.now());

        logger.info("Setting parking ticket leaving timestamp. Id: " + ticket.getId());

        return Uni.createFrom().item(ticket)
                .map(x -> SuccessReply.newBuilder()
                    .setSuccess(true).build());
    }
}
