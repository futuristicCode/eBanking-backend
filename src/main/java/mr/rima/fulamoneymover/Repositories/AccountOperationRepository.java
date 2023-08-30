package mr.rima.fulamoneymover.Repositories;

import mr.rima.fulamoneymover.Entities.AccountOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperations,Long> {


    List<AccountOperations> findAllByBankAccountId(String accountId);

    Page<AccountOperations> findAllByBankAccountId(String accountId, Pageable pageable);
}
