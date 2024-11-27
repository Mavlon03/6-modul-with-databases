package uz.pdp.task3;

import lombok.SneakyThrows;
import uz.pdp.task3.repos.CourseRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Class.forName("uz.pdp.task3.DB");
        while (true){
            System.out.println("""
                    1.Add payment
                    2.Reports
                    """);
            switch (Input.num("Choose menu: ")) {
                case 1 ->{
                    Payment payment = new Payment();
                    Integer sum = Input.num("Qancha summa kiritmoqchisiz: ");
                    payment.setAmount(sum);
                    System.out.println("""
                                    Paytype turini tanlang:
                            1.Cash
                            2.Card
                            3.Bank
                            """);
                    switch (Input.num("Choose:")) {
                        case 1 ->{
                            PayTpe payTpe = PayTpe.CASH;
                            payment.setPayTpe(payTpe);
                        }
                        case 2 ->{
                            PayTpe payTpe = PayTpe.CARD;
                            payment.setPayTpe(payTpe);
                        }
                        case 3 ->{
                            PayTpe payTpe = PayTpe.BANK ;
                            payment.setPayTpe(payTpe);
                        }
                        default -> System.out.println("Incorrect order.");
                    }
                    System.out.println(payment.getPayTpe());
                    Connection connection = DB.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into payment(amount , paytype) values (?,?)");
                    preparedStatement.setInt(1,payment.getAmount());
                    preparedStatement.setString(2, String.valueOf(payment.getPayTpe()));
                    preparedStatement.executeUpdate();
                    System.out.println("Payment successfully added.");
                }
                case 2 ->{

                    System.out.println("""
                            1.Har bir kursdagi talabalar soni
                            2.Har bir kurs uchun tolov summasi
                            3.Course lar royxati.
                            """);
                    switch (Input.num("Choose menu")) {
                        case 1 ->{
                            Map<String, Integer> byId = CourseRepo.getById();
                            System.out.println("Kursdagi talabalr soni: ");
                            byId.forEach((courseName, count) ->{
                                System.out.println(courseName + ": " + count + " ta talaba");
                            });
                        }
                        case 2 ->{
                            Map<String, Integer> totalPayment = CourseRepo.getTotalPayment();
                            System.out.println("Kurslarning umumiy summasi: ");
                            totalPayment.forEach((courseName, totalPay) ->{
                                System.out.println(courseName + ": " + totalPay + " so'm");
                            });
                        }
                        case 3 ->{
                            Map<String, Integer> totalStudentOrderById = CourseRepo.getTotalStudentOrderById();
                            totalStudentOrderById.forEach((courseName, count) ->{
                                System.out.println(courseName + " " + count + " ta");
                            });
                        }

                        default -> System.out.println("Incorrect order");
                    }

                }
            }
        }


    }
}
