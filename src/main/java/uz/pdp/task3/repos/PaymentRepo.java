package uz.pdp.task3.repos;

import lombok.SneakyThrows;
import uz.pdp.task3.DB;
import uz.pdp.task3.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {

    @SneakyThrows
    public static List<Payment> findAllPayment(){
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from payment order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int amount = resultSet.getInt("amount");
            String paytype = resultSet.getString("paytype");
            int studentId = resultSet.getInt("student_id");
            payments.add(new Payment(id,amount,studentId));
        }
        return payments;
    }
}
