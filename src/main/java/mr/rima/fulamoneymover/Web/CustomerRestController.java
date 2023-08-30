package mr.rima.fulamoneymover.Web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.rima.fulamoneymover.DTOs.CustomerDTO;
import mr.rima.fulamoneymover.Exceptions.CustomerNotFoundException;
import mr.rima.fulamoneymover.Services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {

    private CustomerService customerService;

    @RequestMapping("/customers")
    public List<CustomerDTO> customers(){
        return customerService.listCustomer();

    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomer(customerDTO);
    }
    @PutMapping("/customer/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return customerService.updateCustomer(customerDTO);

    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomer(id);
    }

}
