package feri.ita.Repository;

import feri.ita.Models.ParkingTicket;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParkingTicketRepository implements PanacheRepository<ParkingTicket> {

}
