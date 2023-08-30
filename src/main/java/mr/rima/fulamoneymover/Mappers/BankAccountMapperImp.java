//package mr.rima.fulamoneymover.Mappers;
//
//import mr.rima.fulamoneymover.DTOs.BankAccountDTO;
//import mr.rima.fulamoneymover.DTOs.CurrentBankAccountDTO;
//import mr.rima.fulamoneymover.DTOs.CustomerDTO;
//import mr.rima.fulamoneymover.DTOs.SavingBankAccountDTO;
//import mr.rima.fulamoneymover.Entities.BankAccount;
//import mr.rima.fulamoneymover.Entities.CurrentAccount;
//import mr.rima.fulamoneymover.Entities.Customer;
//import mr.rima.fulamoneymover.Entities.SavingAccount;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BankAccountMapperImp {
//
//    private CustomerMapperImp customerMapperImp;
//
//    public BankAccountDTO fromBankAccount(BankAccount bankAccount){
//        BankAccountDTO bankAccountDTO = new BankAccountDTO();
//
//        BeanUtils.copyProperties(bankAccount,bankAccountDTO);
//
////        customerDTO.setId(customer.getId());
////        customerDTO.setName(customer.getName());
////        customerDTO.setEmail(customer.getEmail());
//        return bankAccountDTO;
//    }
//
//    public BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO){
//        BankAccount bankAccount = new BankAccount();
//
//        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
//
////        customer.setId(customerDTO.getId());
////        customer.setName(customerDTO.getName());
////        customer.setEmail(customerDTO.getEmail());
//        return bankAccount;
//    }
//
//    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
//        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
//        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
//
//        CustomerDTO customerDTO = customerMapperImp.fromCustomer(savingAccount.getCustomer());
//        savingBankAccountDTO.setCustomerDTO(customerDTO);
//
//        return savingBankAccountDTO;
//    }
//
//    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
//        SavingAccount savingAccount = new SavingAccount();
//        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
//
//        Customer customer = customerMapperImp.fromCustomerDTO(savingBankAccountDTO.getCustomerDTO());
//        savingAccount.setCustomer(customer);
//
//        return savingAccount;
//
//    }
//
//    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
//        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
//        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
//
//        CustomerDTO customerDTO = customerMapperImp.fromCustomer(currentAccount.getCustomer());
//        currentBankAccountDTO.setCustomerDTO(customerDTO);
//        return currentBankAccountDTO;
//
//    }
//
//    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
//        CurrentAccount currentAccount = new CurrentAccount();
//        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
//
//        Customer customer = customerMapperImp.fromCustomerDTO(currentBankAccountDTO.getCustomerDTO());
//        currentAccount.setCustomer(customer);
//        return currentAccount;
//    }
//
//
//
//}
