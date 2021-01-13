package dao;

import model.Customer;
import model.CustomerType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DAOHelper implements BaseDAO<Customer> {
    public static boolean created = false;
    private static final String SELECT_CUSTOMERS_BY_ID = "select id,fullname,dateofbirth,address,gender,email, phonenumber, cmt, province_IdProvince, customertype_Id from products where id =?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from customers";
    private static final String SELECT_SEARCH_CUSTOMERS = "select * from customer.customers";


    public Customer selectCustomer(int id) throws SQLException {
        Customer customer = null;
        setConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("fullname");
                Date dayOfBirth = rs.getDate("dayofbirth");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                int cmt = rs.getInt("cmt");
                int idProvince = rs.getInt("province_IdProvince");
                int idCustomerType = rs.getInt("customertype_Id");
                customer = new Customer(id, fullName, dayOfBirth, address, gender, email, phoneNumber, cmt, idProvince, idCustomerType);
            }
            disConnection();
        return customer;
    }


    public List<Customer> selectAllCustomer() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        setConnection();
        PreparedStatement st = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("fullname");
//                String check = rs.getString("dayofbirth");
//                Date dateOfBirth = Date.valueOf(check);
            Date dateOfBirth = Date.valueOf(rs.getString("dateofbirth"));
            String address = rs.getString("address");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phoneNumber");
            int cmt = rs.getInt("cmt");
            int idProvince = rs.getInt("province_IdProvince");
            int idCustomerType = rs.getInt("customertype_Id");
            customers.add(new Customer(id, fullName, dateOfBirth, address, gender, email, phoneNumber, cmt, idProvince, idCustomerType));
        }
        ProvinceDAO provinceDAO = new ProvinceDAO();
        for (Customer customer : customers) {
            customer.setProvince(provinceDAO.findById(customer.getIdProvince()));
        }
        disConnection();
        return customers;
}


    public List<Customer> searchByNameCustomer(String fullName) throws SQLException {
        List<Customer> searchCustomerName = new ArrayList<>();
        setConnection();
        String search = SELECT_SEARCH_CUSTOMERS + " where fullname like " + "'%" + fullName + "%'";
        PreparedStatement st = connection.prepareStatement(search);
        System.out.println(st);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String fullname = rs.getString("fullname");
            Date dateOfBirth = Date.valueOf(rs.getString("dateofbirth"));
            String address = rs.getString("address");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phoneNumber");
            int cmt = rs.getInt("cmt");
            int idProvince = rs.getInt("province_IdProvince");
            int idCustomerType = rs.getInt("customertype_Id");
            searchCustomerName.add(new Customer(id, fullname, dateOfBirth, address, gender, email, phoneNumber, cmt, idProvince, idCustomerType));
        }
        ProvinceDAO provinceDAO = new ProvinceDAO();
        for (Customer customer : searchCustomerName) {
            customer.setProvince(provinceDAO.findById(customer.getIdProvince()));
        }
        CustomerTypeDAO customerTypeDAO = new CustomerTypeDAO();
        for (Customer customer : searchCustomerName) {
            customer.setCustomerType(customerTypeDAO.findById(customer.getIdCustomerType()));
        }
        disConnection();
        return searchCustomerName;
    }

    public Customer getCustomerById(int id) throws SQLException {
        Customer customer = null;
        setConnection();

        String query = "{CALL get_customer_by_id(?)}";

        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, id);

        // Step 3: Execute the query or update query

        ResultSet rs = callableStatement.executeQuery();

        // Step 4: Process the ResultSet object.

        while (rs.next()) {

            String fullName = rs.getString("fullname");
            Date dateOfBirth = Date.valueOf(rs.getString("dateofbirth"));
            String address = rs.getString("address");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phonenumber");
            int cmt = rs.getInt("cmt");
            int idProvince = rs.getInt("province_IdProvince");
            int idCustomerType = rs.getInt("customertype_Id");
            customer = new Customer(id, fullName, dateOfBirth, address, gender, email, phoneNumber, cmt, idProvince, idCustomerType);
        }
        System.out.println(customer.toString());
        disConnection();
        return customer;
    }

    public void insertCustomerStore(Customer customer) throws SQLException {
        String query = "{CALL insert_customer(?,?,?,?,?,?,?,?,?)}";

        // try-with-resource statement will auto close the connection.
        setConnection();

        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setString(1, customer.getFullname());

        callableStatement.setDate(2, customer.getDateOfBirth());

        callableStatement.setString(3, customer.getAddress());

        callableStatement.setString(4, customer.getGender());

        callableStatement.setString(5, customer.getEmail());

        callableStatement.setString(6, customer.getPhoneNumber());

        callableStatement.setInt(7, customer.getCmt());

        callableStatement.setInt(8, customer.getIdProvince());

        callableStatement.setInt(9, customer.getIdCustomerType());

        System.out.println(callableStatement);

        callableStatement.executeUpdate();
        disConnection();
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

    @Override
    public List<Customer> findAll() throws SQLException {
        return null;
    }

    @Override
    public Customer findById(int id) throws Exception {
        return null;
    }

    @Override
    public boolean save(Customer object) throws Exception {
        setConnection();
        String SAVE_SQL = "INSERT INTO customers " + "  (fullname, dateofbirth, address, gender, email, phonenumber, cmt, province_IdProvince, customertype_Id) VALUES " +
                " (?, ?, ?,?,?,?,?,?,?);";
        PreparedStatement st = prepareStatement(object, SAVE_SQL);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public boolean update(Customer object) throws Exception {
        setConnection();
        String UPDATE_SQL = "update customers set fullname = ?,dateofbirth= ?, address =?, gender =?, email =?, phonenumber=?, cmt=?, province_IdProvince=?, customertype_Id=?  where id = ?;";
        PreparedStatement st = prepareStatement(object, UPDATE_SQL);
        st.setInt(10, object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public boolean delete(Customer object) throws Exception {
        setConnection();
        String DELETE_SQL = "delete from customers where id = ?;";
        PreparedStatement st = connection.prepareStatement(DELETE_SQL);
        st.setInt(1, object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    private PreparedStatement prepareStatement(Customer object, String INSERT_CUSTOMERS_SQL) throws SQLException {
        PreparedStatement st = connection.prepareStatement(INSERT_CUSTOMERS_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getFullname());
        st.setDate(2, object.getDateOfBirth());
        st.setString(3, object.getAddress());
        st.setString(4, object.getGender());
        st.setString(5, object.getEmail());
        st.setString(6, object.getPhoneNumber());
        st.setInt(7, object.getCmt());
        st.setInt(8, object.getIdProvince());
        st.setInt(9, object.getIdCustomerType());
        return st;
    }
}
