package uz.pdp.task3;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;

public class DB {
    @Getter
    private static HikariDataSource dataSource;

    static {
        init();
    }
    private static void init() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/task3db");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("root123");
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(15);
        hikariConfig.setConnectionTimeout(30000);
        dataSource  = new HikariDataSource(hikariConfig);
    }

    @SneakyThrows
    public static Connection getConnection(){
        return dataSource.getConnection();
    }
}
