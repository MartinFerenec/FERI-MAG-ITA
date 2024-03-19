package com.feri.ita.paymentservice;

import com.feri.ita.paymentservice.Controllers.PaymentController;
import com.feri.ita.paymentservice.Models.ParkingTicket;
import com.feri.ita.paymentservice.Models.ParkingTicketStatus;
import com.feri.ita.paymentservice.Services.PaymentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void getParkingTicket_Found_ReturnsOk() throws Exception{
        ParkingTicket parkingTicket = new ParkingTicket("MB BE698", LocalDateTime.now());
        parkingTicket.setId(1L);

        when(paymentService.getParkingTicket(anyLong())).thenReturn(Optional.of(parkingTicket));

        mockMvc.perform(get("/parkingTicket/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.licensePlate", is(parkingTicket.getLicensePlate())));
    }

    @Test
    public void getParkingTicket_NotFound_ReturnsNotFound() throws Exception{
        when(paymentService.getParkingTicket(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/parkingTicket/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void isTicketPaid_Yes_ReturnsOkPaid() throws Exception{
        when(paymentService.isTicketPaid(anyLong())).thenReturn(Optional.of(new ParkingTicketStatus(true)));

        mockMvc.perform(get("/isTicketPaid/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isPaid", is(true)));
    }

    @Test
    public void isTicketPaid_No_ReturnsOkNotPaid() throws Exception{
        when(paymentService.isTicketPaid(anyLong())).thenReturn(Optional.of(new ParkingTicketStatus(false)));

        mockMvc.perform(get("/isTicketPaid/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isPaid", is(false)));
    }

    @Test
    public void isTicketPaid_NotFound_ReturnsNotFound() throws Exception{
        when(paymentService.isTicketPaid(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/isTicketPaid/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void markAsPaid_NotPaidYet_ReturnsOk() throws Exception{
        ParkingTicket parkingTicket = new ParkingTicket("MB BE698", LocalDateTime.now());
        parkingTicket.setId(1L);
        parkingTicket.setPaidTimestamp(LocalDateTime.now());

        when(paymentService.markAsPaid(anyLong())).thenReturn(Optional.of(parkingTicket));

        mockMvc.perform(patch("/markAsPaid/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.paidTimestamp", notNullValue()));
    }

    @Test
    public void markAsPaid_AlreadyPaidOrParkingTicketNotFound_ReturnsBadRequest() throws Exception{
        when(paymentService.markAsPaid(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(patch("/markAsPaid/1"))
            .andExpect(status().isBadRequest());
    }
}