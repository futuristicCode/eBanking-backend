package mr.rima.fulamoneymover.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.rima.fulamoneymover.DTOs.*;
import mr.rima.fulamoneymover.Entities.*;
import mr.rima.fulamoneymover.Enums.OperationType;
import mr.rima.fulamoneymover.Exceptions.BalanceNotSufficientException;
import mr.rima.fulamoneymover.Exceptions.BankAccountNotFoundException;
import mr.rima.fulamoneymover.Exceptions.CustomerNotFoundException;
import mr.rima.fulamoneymover.Mappers.BankMapperImpl;
import mr.rima.fulamoneymover.Repositories.AccountOperationRepository;
import mr.rima.fulamoneymover.Repositories.BankAccountRepository;
import mr.rima.fulamoneymover.Repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{

    private BankAccountRepository bankAccountRepository;

    private CustomerRepository customerRepository;

    private AccountOperationRepository accountOperationRepository;

    private BankMapperImpl bankMapper;




    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer == null)
            throw new CustomerNotFoundException(" Customer Not Found");

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);

        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);

        return bankMapper.fromCurrentBankAccount(savedBankAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer == null)
            throw new CustomerNotFoundException(" Customer Not Found");

        SavingAccount savingAccount= new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);

        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);

        return bankMapper.fromSavingBankAccount(savingAccount);
    }


    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {

        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new BankAccountNotFoundException(" BankAccount Not Found"));
        if(bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException(" Balance Not Sufficient");

        AccountOperations accountOperations = new AccountOperations();
        accountOperations.setType(OperationType.DEBIT);
        accountOperations.setAmount(amount);
        accountOperations.setDescription(description);
        accountOperations.setOperationDate(new Date());
        accountOperations.setBankAccount(bankAccount);
//        Saved Operation
        accountOperationRepository.save(accountOperations);
//       Update AmountAccount
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);


    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {

        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new BankAccountNotFoundException(" BankAccount Not Found"));

        AccountOperations accountOperations = new AccountOperations();
        accountOperations.setType(OperationType.CREDIT);
        accountOperations.setAmount(amount);
        accountOperations.setDescription(description);
        accountOperations.setOperationDate(new Date());
        accountOperations.setBankAccount(bankAccount);
//        Saved Operation
        accountOperationRepository.save(accountOperations);
//       Update AmountAccount
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource,amount," Transfer to " + accountIdDestination);
        credit(accountIdDestination,amount," Transfer to " + accountIdSource);

    }


    @Override
    public List<BankAccountDTO> bankAccountList(){
        List<BankAccount> bankAccounts= bankAccountRepository.findAll();
        List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().map(bankAccount -> {
            if(bankAccount instanceof SavingAccount){
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return bankMapper.fromSavingBankAccount(savingAccount);
            }else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankMapper.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());

        return bankAccountDTOS;
    }


    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new BankAccountNotFoundException(" BankAccount Not Found"));
        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankMapper.fromSavingBankAccount(savingAccount);
        }else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return bankMapper.fromCurrentBankAccount(currentAccount);
        }
    }


    @Override
    public List<AccountOperationsDTO> accountHistory(String accountId){
        List<AccountOperations> accountOperations = accountOperationRepository.findAllByBankAccountId(accountId);

        return accountOperations.stream().map(ops->bankMapper.fromAccountOperations(ops)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount= bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("Account Not Found");
        Page<AccountOperations> accountOperations = accountOperationRepository.findAllByBankAccountId(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<AccountOperationsDTO> accountOperationsDTOS = accountOperations.getContent().stream().map(op -> bankMapper.fromAccountOperations(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationsDTOS(accountOperationsDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setOperationDate(new Date());  // Testtttttttt
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPage(accountOperations.getTotalPages());

        return accountHistoryDTO;

    }

}
