package mr.rima.fulamoneymover.DTOs;


import lombok.Data;
import mr.rima.fulamoneymover.Enums.AccountStatus;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO{

        private String id;
        private double balance;
        private Date createdAt;
        private AccountStatus status;
        private CustomerDTO customerDTO;
        private double overDraft;
}
