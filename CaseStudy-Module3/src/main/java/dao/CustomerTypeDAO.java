package dao;

import model.Customer;
import model.CustomerType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeDAO extends DAOHelper implements BaseDAO<CustomerType> {

    public static boolean created = false;
    private static final String SELECT_ALL_CUSTOMERTYPE = "select * from customertype";
    private static final String SELECT_CUSTOMERTYPE_BY_ID = "select id,CustomerTypeName from customertype where id =?";
    private static final String SELECT_VIEW_CUSTOMERTYPE = "select * FROM customers where customertype_Id=?";


    @Override
    public List<CustomerType> findAll() throws SQLException {
        List<CustomerType> customerTypes = new ArrayList<>();
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERTYPE);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String customerTypeName = rs.getString("CustomerTypeName");
                customerTypes.add(new CustomerType(id, customerTypeName));
            }
            disConnection();
            return customerTypes;
        }

    @Override
    public CustomerType findById(int id) throws SQLException {
        CustomerType customerType = null;
        setConnection();
        String query = "{CALL get_customertype_by_id(?)}";


        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, id);

        // Step 3: Execute the query or update query

        ResultSet rs = callableStatement.executeQuery();

        // Step 4: Process the ResultSet object.

        while (rs.next()) {
            String customerTypeName = rs.getString("CustomerTypeName");
            customerType = new CustomerType(id, customerTypeName);

        }
        disConnection();
        return customerType;
    }

    @Override
    public boolean save(CustomerType object) throws Exception {
        setConnection();

        String SAVE_SQL = "INSERT INTO customertype " + "  (CustomerTypeName) VALUES " + " (?);";
        PreparedStatement st;
        st = prepareStatement(object, SAVE_SQL);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    private PreparedStatement prepareStatement(CustomerType object, String SAVE_SQL) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SAVE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getTypeCusName());
        return st;
    }

    @Override
    public boolean update(CustomerType object) throws Exception {
        return false;
    }

    @Override
    public boolean delete(CustomerType object) throws Exception {
        setConnection();
        String DELETE_SQL = "delete from customertype where id = ?;";
        PreparedStatement st = connection.prepareStatement(DELETE_SQL);
        st.setInt(1, object.getIdCustomerType());
        boolean rt = st.executeUpdate() > 0;
        disConnection();
        return rt;
    }

    public void insertTypeCustomerStore(CustomerType customerType) throws SQLException {
        String query = "{CALL insert_customertype(?)}";

        setConnection();

        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setString(1, customerType.getTypeCusName());

        System.out.println(callableStatement);

        callableStatement.executeUpdate();

        disConnection();

    }

    public List<CustomerType> selectAllCustomerType() throws SQLException {
        List<CustomerType> customerTypes = new ArrayList<>();
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERTYPE);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String customerTypeName = rs.getString("CustomerTypeName");
            customerTypes.add(new CustomerType(id, customerTypeName));
        }
        disConnection();
        return customerTypes;
    }

    public CustomerType selectCustomerType(int id) throws SQLException {
        CustomerType customerType = null;
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERTYPE_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String customerTypeName = rs.getString("CustomerTypeName");
            customerType = new CustomerType(id, customerTypeName);
        }
        disConnection();
        return customerType;
    }


    public List<Customer> selectViewAllCustomerType(int idType) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VIEW_CUSTOMERTYPE);
        preparedStatement.setInt(1, idType);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
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
}
