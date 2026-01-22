package com.assigment_2_thuongnt87.service;

import com.assigment_2_thuongnt87.dto.checkout.ReserveResponse;
import com.assigment_2_thuongnt87.entities.inventory.InventoryReservation;
import com.assigment_2_thuongnt87.entities.inventory.ReservationStatus;

import java.util.UUID;

public interface InventoryReservationService {
    ReserveResponse reserveFromCart(UUID cartToken, Integer holdMinutes);
    void cancelReservation(UUID reservationToken);
    void releaseExpiredReservations();
    void releaseById(UUID reservationToken);

}
