package opintoapp.dao;

import java.nio.file.*;
import java.sql.*;
import java.util.function.Consumer;

/**
 * Tietokantaa edustava luokka.
 *
 */
public class Database {

    private String databaseAdress;

    /**
     * Konstruktori, joka luo tietokannan ja tietokantataulut ensimmäisen
     * käynnistyksen yhteydessä.
     *
     * @param databaseAdress Tietokannan polku.
     * @throws ClassNotFoundException
     */
    public Database(String databaseAdress) throws ClassNotFoundException {
        this.databaseAdress = databaseAdress;
        try {
            Connection conn = DriverManager.getConnection(databaseAdress);
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS"
                    + " User (id integer PRIMARY KEY,"
                    + " username varchar(200),"
                    + " name varchar(200),"
                    + " password varchar(200)"
                    + ");");

            stmt.execute("CREATE TABLE IF NOT EXISTS"
                    + " Course (id integer PRIMARY KEY,"
                    + " name varchar(200),"
                    + " credits integer,"
                    + " grade integer,"
                    + " semester varchar(20),"
                    + " user_username varchar(200),"
                    + " FOREIGN KEY (user_username) REFERENCES User(username)"
                    + ");");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodi ottaa yhteyden tietokantaan SQL-lauseiden suorittamista varten.
     *
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAdress);
    }

    public void deleteUpdateOrInsert(String sql, Consumer<PreparedStatement> consumer) throws SQLException {
        Connection conn = this.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        consumer.accept(stmt);

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
}
