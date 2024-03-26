package feri.ita;

import org.hibernate.service.spi.InjectService;

import feri.ita.Models.ParkingTicket;
import feri.ita.Repository.ParkingTicketRepository;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

@GrpcService
public class TicketMachineServiceGrpcService implements TicketMachineServiceGrpc {

    @Inject
    ParkingTicketRepository parkingTicketRepo;

    @Override
    @Blocking
    public Uni<TicketReply> issueTicket(TicketRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
                .map(msg -> TicketReply.newBuilder().setMessage(msg).build());
    }

    @Override
    @Blocking
    public Uni<TicketReply> getTicket(IdRequest request){
        ParkingTicket ticket = parkingTicketRepo.findById((long)request.getId());
        return Uni.createFrom().item("License plate: " + ticket.getLicensePlate())
                .map(msg -> TicketReply.newBuilder().setMessage(msg).build());
    }

    
}
