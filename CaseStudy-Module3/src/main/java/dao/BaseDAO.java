package dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    List<T> findAll() throws SQLException;
    T findById (int id) throws Exception;
    boolean save(T object) throws Exception;
    boolean update(T object) throws Exception;
    boolean delete(T object) throws  Exception;
}
