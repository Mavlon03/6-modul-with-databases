package uz.pdp.task3.repos;

import lombok.SneakyThrows;
import uz.pdp.task3.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CourseRepo {
   public static Map<String, Integer> getById(){
       Map<String, Integer> courseStudent = new HashMap<>();
       try (
               Connection connection = DB.getConnection()
       ){
           PreparedStatement preparedStatement = connection.prepareStatement("""
                   select c.name as course_name, count(s.id) as student_count
                                       from student s
                                       join groups g on g.id = s.group_id
                                       join course c on c.id = g.course_id
                                       group by c.name
                   """);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               courseStudent.put(resultSet.getString("course_name"),resultSet.getInt("student_count"));
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return courseStudent;
   }

    public static Map<String, Integer> getTotalPayment() {
       Map<String, Integer> coursePaymentSum = new HashMap<>();
        try (
                Connection connection = DB.getConnection();
                ){
            PreparedStatement preparedStatement = connection.prepareStatement("""
                            select c.name as course_name, sum(p.amount)
                                                from payment p
                                                join student s on s.id = p.student_id
                                                join groups g on g.id = s.group_id
                                                join course c on c.id = g.course_id
                                                group by c.name
                    """);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                coursePaymentSum.put(resultSet.getString("course_name"), resultSet.getInt("sum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursePaymentSum;
    }

    @SneakyThrows
    public static Map<String, Integer> getTotalStudentOrderById() {
       Map<String, Integer> getByOrderMenu = new HashMap<>();
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("""
                select c.name as group_name, count(s.id) from student s
                join groups g on g.id = s.group_id
                join course c on c.id = g.course_id
                group by c.name
                order by sum(s.id)desc;
                """);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String groupName = resultSet.getString("group_name");
            int count = resultSet.getInt("count");
            getByOrderMenu.put(groupName, count);
        }
        return getByOrderMenu;
    }
}
