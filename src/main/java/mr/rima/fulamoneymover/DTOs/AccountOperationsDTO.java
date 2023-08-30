package mr.rima.fulamoneymover.DTOs;


import lombok.Data;
import mr.rima.fulamoneymover.Entities.BankAccount;
import mr.rima.fulamoneymover.Enums.OperationType;

import java.util.Date;


@Data
public class AccountOperationsDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
