package lk.uok.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Padma Gnanapiya (SE/2017/014)
 */


public class DBConnection {
    //1st Step
    private static DBConnection dbConnection=null;
    private Connection connection;
    //2nd Step
    private DBConnection() throws ClassNotFoundException, SQLException {
        try (InputStream input = new FileInputStream("./.idea/libraries/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            connection = DriverManager.getConnection(prop.getProperty("localhost"),prop.getProperty("root"),prop.getProperty("1234"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //3rd Step
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        //4th Step
        return (dbConnection==null)?
                (dbConnection=new DBConnection()):
                (dbConnection);
    }
    public Connection getConnection(){
        return connection;
    }
}

