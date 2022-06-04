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

        // jdbc:oracle:thin@<host>:<port>:xe
        mysqlDataSource.setURL("jdbc:oracle:thin:@"+ host + ":" + port +":xe");
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mysqlDataSource;
    }

    public static DataSource getMyMariaDBDataSource(){

        MysqlDataSource mysqlDataSource = new MysqlDataSource();


        String host = MyConfig.getInstance().getDBHost();
        String port = MyConfig.getInstance().getDBPort();
        String schema = MyConfig.getInstance().getDBSchema();
        String user = MyConfig.getInstance().getUsername();
        String password = MyConfig.getInstance().getPassword();

        // jdbc:mysql://<host>:<port>/<schema>
        mysqlDataSource.setURL("jdbc:mysql://"+ host + ":" + port +"/"+ schema);
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);

        return mysqlDataSource;
    }

}
