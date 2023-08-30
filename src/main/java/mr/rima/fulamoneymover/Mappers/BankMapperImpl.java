package mr.rima.fulamoneymover.Mappers;

import mr.rima.fulamoneymover.DTOs.*;
import mr.rima.fulamoneymover.Entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class BankMapperImpl {

    public BankAccountDTO fromBankAccount(BankAccount bankAccount){
        BankAccountDTO bankAccountDTO = new BankAccountDTO();

        BeanUtils.copyProperties(bankAccount,bankAccountDTO);

//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());
        return bankAccountDTO;
    }

    public BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO){
        BankAccount bankAccount = new BankAccount();

        BeanUtils.copyProperties(bankAccountDTO,bankAccount);

//        customer.setId(customerDTO.getId());
//        customer.setName(customerDTO.getName());
//        customer.setEmail(customerDTO.getEmail());
        return bankAccount;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);

        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());


        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);

        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));

        return savingAccount;

    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);

        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;

    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);

        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

/////////////////////////////// CUSTOMERS ////////////////////////////////////////////

    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();

        BeanUtils.copyProperties(customer,customerDTO);

//        customerDTO.setId(customer.getId());
//        customerDTO.setName(customer.getName());
//        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();

        BeanUtils.copyProperties(customerDTO,customer);

//        customer.setId(customerDTO.getId());
//        customer.setName(customerDTO.getName());
//        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    public AccountOperationsDTO fromAccountOperations(AccountOperations accountOperations){
        AccountOperationsDTO accountOperationsDTO = new AccountOperationsDTO();
        BeanUtils.copyProperties(accountOperations,accountOperationsDTO);

        return accountOperationsDTO;

    }

    public AccountOperations fromAccountOperationsDTO(AccountOperationsDTO accountOperationsDTO){

        AccountOperations accountOperations = new AccountOperations();
        BeanUtils.copyProperties(accountOperationsDTO,accountOperations);
        return accountOperations;

    }

}
