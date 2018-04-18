
package opintoapp.dao;

import java.sql.*;
import java.util.List;

public interface Dao<Value, Key> {
    
//    void Create(Value v) throws SQLException;
    Value findOne(String s, String s2) throws SQLException;
//    List<Value> getAll() throws SQLException;
    
}
