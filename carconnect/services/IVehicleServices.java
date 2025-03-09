package services;

import models.VehicleModal;

public interface IVehicleServices {
    VehicleModal getVehicleById(int vehicleId);
    VehicleModal getVehicleByRegistrationNumber(String registrationNumber);
    VehicleModal registerVehicle(VehicleModal vehicle);
    VehicleModal updateVehicle(VehicleModal vehicle);
    boolean deleteVehicle(int vehicleId);
}