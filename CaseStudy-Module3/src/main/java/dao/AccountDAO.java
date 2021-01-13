package dao;

import model.Account;
import model.Customer;
import model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends DAOHelper implements BaseDAO<Account> {
    public static boolean created = false;

    private static final String SELECT_ACCOUNT_BY_ID = "select id,user,password from account where id =?";
    private static final String SELECT_ALL_ACCOUNT = "select * from account";
    private static final String UPDATE_ACCOUNT_SQL = "update account set  password=?  where id = ?;";

    public AccountDAO() {
    }

    public Account selectAccount(int id) throws SQLException {
        Account account = null;
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            String user = rs.getString("user");
            String password = rs.getString("password");
            account = new Account(id, user, password);
        }
        disConnection();
        return account;
    }

    public List<Account> selectAllAccount() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNT);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            int id = rs.getInt("id");
            String password = rs.getString("password");
            accounts.add(new Account(id, password));
        }
        disConnection();
        return accounts;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        setConnection();
        String SELECT_ALL_ACCOUNT = "select * from account";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNT);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            int id = rs.getInt("id");
            String user = rs.getString("user");
            String password = rs.getString("password");
            accounts.add(new Account(id, user, password));
        }
        disConnection();
        return accounts;
    }

    @Override
    public Account findById(int id) throws SQLException {
        Account account = null;

        String query = "{CALL get_account_by_id(?)}";
        setConnection();

        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, id);

        // Step 3: Execute the query or update query

        ResultSet rs = callableStatement.executeQuery();

        // Step 4: Process the ResultSet object.

        while (rs.next()) {
            String user = rs.getString("user");
            String password = rs.getString("password");
            account = new Account(id, user, password);

        }
        disConnection();
        return account;
    }

    private PreparedStatement prepareStatement(Account object, String SAVE_SQL) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SAVE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getPassword());
        st.setInt(2, object.getId());
        return st;
    }

    @Override
    public boolean save(Account object) throws Exception {
        return false;
    }

    @Override
    public boolean update(Account object) throws Exception {
        setConnection();
        String UPDATE_SQL = "update account set  password=?  where id = ?;";
        PreparedStatement st = prepareStatement(object, UPDATE_SQL);
        st.setInt(2, object.getId());
        boolean rt = st.executeUpdate() > 0;
        disConnection();
        return rt;
    }

    @Override
    public boolean delete(Account object) throws Exception {
        return false;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
