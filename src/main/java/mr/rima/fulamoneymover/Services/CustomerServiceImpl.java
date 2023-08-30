package mr.rima.fulamoneymover.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.rima.fulamoneymover.DTOs.CustomerDTO;
import mr.rima.fulamoneymover.Entities.*;
import mr.rima.fulamoneymover.Exceptions.CustomerNotFoundException;
import mr.rima.fulamoneymover.Mappers.BankMapperImpl;
import mr.rima.fulamoneymover.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{


    private CustomerRepository customerRepository;

    private BankMapperImpl bankMapper;



    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving New Customer");
        Customer customer = bankMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return bankMapper.fromCustomer(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Update Customer");
        Customer customer = bankMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return bankMapper.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId){
        log.info("Delete Customer");
        customerRepository.deleteById(customerId);
    }




    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> customers= customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> bankMapper.fromCustomer(customer)).collect(Collectors.toList());

        return customerDTOS;
    }


    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException("Customer Not Found"));

        return bankMapper.fromCustomer(customer);
    }
}
