package mr.rima.fulamoneymover.Entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CC")
@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentAccount extends BankAccount{
    private double overDraft;
}
