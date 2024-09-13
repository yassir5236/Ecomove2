package console.ui;

import model.Reservation;
import service.interfaces.IReservationService;

import java.util.Scanner;
import java.util.UUID;

public class ReservationUI {
    private final IReservationService reservationService;

    public ReservationUI(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

}