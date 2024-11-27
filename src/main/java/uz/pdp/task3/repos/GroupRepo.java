package uz.pdp.task3.repos;

import lombok.SneakyThrows;
import uz.pdp.task3.DB;
import uz.pdp.task3.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupRepo {
//    @SneakyThrows
//    public static void saveGroup(Group group){
//        Connection connection = DB.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into groups(name) values (?)");
//        preparedStatement.setString(1,group.getName());
//        preparedStatement.executeUpdate();
//    }


    @SneakyThrows
    public static List<Group> findAllGroups(){
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from groups order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Group> groups = new ArrayList<>();
        while (resultSet.next()){
            int id  = resultSet.getInt("id");
            String groupName = resultSet.getString("name");

            groups.add(new Group(id, groupName));
        }
        return groups;
    }


}
