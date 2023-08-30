//package mr.rima.fulamoneymover.Mappers;
//
//import mr.rima.fulamoneymover.DTOs.CustomerDTO;
//import mr.rima.fulamoneymover.Entities.Customer;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomerMapperImp {
//
//    public CustomerDTO fromCustomer(Customer customer){
//        CustomerDTO customerDTO = new CustomerDTO();
//
//        BeanUtils.copyProperties(customer,customerDTO);
//
////        customerDTO.setId(customer.getId());
////        customerDTO.setName(customer.getName());
////        customerDTO.setEmail(customer.getEmail());
//        return customerDTO;
//    }
//
//    public Customer fromCustomerDTO(CustomerDTO customerDTO){
//        Customer customer = new Customer();
//
//        BeanUtils.copyProperties(customerDTO,customer);
//
////        customer.setId(customerDTO.getId());
////        customer.setName(customerDTO.getName());
////        customer.setEmail(customerDTO.getEmail());
//        return customer;
//    }
//
//
//}
