package mr.rima.fulamoneymover.Services;

import mr.rima.fulamoneymover.DTOs.*;
import mr.rima.fulamoneymover.Entities.BankAccount;
import mr.rima.fulamoneymover.Entities.CurrentAccount;
import mr.rima.fulamoneymover.Entities.SavingAccount;
import mr.rima.fulamoneymover.Exceptions.BalanceNotSufficientException;
import mr.rima.fulamoneymover.Exceptions.BankAccountNotFoundException;
import mr.rima.fulamoneymover.Exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {




    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();


    List<AccountOperationsDTO> accountHistory(String accountId);


    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
