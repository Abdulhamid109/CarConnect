package services;

import models.AdminModal;

public interface IAdminServices {
    AdminModal getAdminById(int adminId);
    AdminModal getAdminByUsername(String username);
    AdminModal registerAdmin(AdminModal admin);
    AdminModal updateAdmin(AdminModal admin);
    boolean deleteAdmin(int adminId);
}
