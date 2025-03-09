package services;

import models.ReservationModal;

public  interface IReservationServices {
    ReservationModal getReservationById(int reservationId);
    ReservationModal getReservationByUserName(String username);
    ReservationModal registerReservation(ReservationModal reservation);
    ReservationModal updateReservation(ReservationModal reservation);
    boolean deleteReservation(int reservationId);
}
