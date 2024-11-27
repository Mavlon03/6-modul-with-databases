package uz.pdp.task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Integer id;
    private Integer amount;
    private PayTpe payTpe = PayTpe.CASH;
    private Integer studentId;

    public Payment(Integer id, Integer amount, Integer studentId) {
        this.id = id;
        this.amount = amount;
        this.studentId = studentId;
    }

    public static Payment getInstanceFromTerminal() {
        return null;
    }
}
