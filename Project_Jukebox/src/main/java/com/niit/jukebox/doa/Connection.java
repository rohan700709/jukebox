//Data Access Object layer(DAO)...have dB related codings
package com.niit.jukebox.doa;
import java.sql.DriverManager;
import java.sql.SQLException;
//Created a class for Connection object
public class Connection {
    //static, so we don't need to create obj everytime.
    //getConnection will throw SQLException
    public static java.sql.Connection getConnection() throws SQLException {
        java.sql.Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Rohan@000");
        return connection;
    }
}
