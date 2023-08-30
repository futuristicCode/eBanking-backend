package mr.rima.fulamoneymover.Repositories;

import mr.rima.fulamoneymover.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
