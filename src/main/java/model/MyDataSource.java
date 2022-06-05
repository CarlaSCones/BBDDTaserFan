package model;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.pool.OracleDataSource;
import properties.MyConfig;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMyOracleDataSource(){

        OracleDataSource mysqlDataSource = null;
        try {
            mysqlDataSource = new OracleDataSource();

        String host = MyConfig.getInstance().getOracleDBHost();
        String port = MyConfig.getInstance().getOracleDBPort();
        String user = MyConfig.getInstance().getOracleUsername();
        String password = MyConfig.getInstance().getOraclePassword();

        mysqlDataSource.setURL("jdbc:oracle:thin:@"+ host + ":" + port +":xe");
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mysqlDataSource;
    }
}
