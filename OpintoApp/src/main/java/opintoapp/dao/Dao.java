
package opintoapp.dao;

import java.sql.*;
import java.util.List;

public interface Dao<Value, Key> {
    
//    void Create(Value v) throws SQLException;
    Value findOne(Key k) throws SQLException;
    List<Value> getAll() throws SQLException;
    
}
