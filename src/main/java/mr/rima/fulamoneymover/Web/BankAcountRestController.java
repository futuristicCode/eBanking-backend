package mr.rima.fulamoneymover.Web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.rima.fulamoneymover.DTOs.AccountHistoryDTO;
import mr.rima.fulamoneymover.DTOs.AccountOperationsDTO;
import mr.rima.fulamoneymover.DTOs.BankAccountDTO;
import mr.rima.fulamoneymover.Exceptions.BankAccountNotFoundException;
import mr.rima.fulamoneymover.Services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class BankAcountRestController {

    private BankAccountService bankAccountService;

    @RequestMapping("/accounts")
    public List<BankAccountDTO> bankAccounts(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/account/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }


    @GetMapping("account/{accountId}/operations")
    public List<AccountOperationsDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("account/{accountId}/pageOperations")
    public AccountHistoryDTO getHistory(@PathVariable String accountId,
                                        @RequestParam(name = "page",defaultValue = "0") int page,
                                        @RequestParam(name = "size" ,defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }


}
