package services;

import models.CustomerModal;

public interface ICustomerService {
    CustomerModal getCustomerById(int customerId);
    CustomerModal getCustomerByUserName(String username);
    CustomerModal registerCustomer(CustomerModal customer);
    CustomerModal updateCustomer(CustomerModal customer);
    boolean deleteCustomer(int customerId);
}
