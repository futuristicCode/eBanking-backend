package mr.rima.fulamoneymover.Services;

import mr.rima.fulamoneymover.DTOs.BankAccountDTO;
import mr.rima.fulamoneymover.DTOs.CustomerDTO;
import mr.rima.fulamoneymover.Entities.BankAccount;
import mr.rima.fulamoneymover.Entities.CurrentAccount;
import mr.rima.fulamoneymover.Entities.SavingAccount;
import mr.rima.fulamoneymover.Exceptions.BalanceNotSufficientException;
import mr.rima.fulamoneymover.Exceptions.BankAccountNotFoundException;
import mr.rima.fulamoneymover.Exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<CustomerDTO> listCustomer();



    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
}
