
package opintoapp.dao;

import java.sql.*;

public class Database {
    
    private String databaseAdress;

    public Database(String databaseAdress) throws ClassNotFoundException{
        this.databaseAdress = databaseAdress;
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(databaseAdress);
    }
}
