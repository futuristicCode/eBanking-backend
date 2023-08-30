package mr.rima.fulamoneymover;

import mr.rima.fulamoneymover.DTOs.BankAccountDTO;
import mr.rima.fulamoneymover.DTOs.CurrentBankAccountDTO;
import mr.rima.fulamoneymover.DTOs.CustomerDTO;
import mr.rima.fulamoneymover.DTOs.SavingBankAccountDTO;
import mr.rima.fulamoneymover.Exceptions.BalanceNotSufficientException;
import mr.rima.fulamoneymover.Exceptions.BankAccountNotFoundException;
import mr.rima.fulamoneymover.Exceptions.CustomerNotFoundException;
import mr.rima.fulamoneymover.Services.BankAccountService;
import mr.rima.fulamoneymover.Services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountService bankAccountService, CustomerService customerService){
        return args -> {
            Stream.of("Oumar","Adama","Rama").forEach(name->{

                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");

                customerService.saveCustomer(customer);

            });

            customerService.listCustomer().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5, customer.getId());


                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccounts){
                for (int i=0;i<10;i++){
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    }else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }

            }

        };

    }
}
