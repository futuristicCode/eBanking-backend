package mr.rima.fulamoneymover.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class AccountHistoryDTO {
    private  String accountId;
    private double balance;
    private Date OperationDate;

    private int currentPage;
    private int totalPage;
    private int pageSize;

    List<AccountOperationsDTO> accountOperationsDTOS;

}
