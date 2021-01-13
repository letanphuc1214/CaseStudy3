package dao;

import model.Customer;
import model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO extends DAOHelper implements BaseDAO<Province> {

    private static final String SELECT_ALL_PROVINCE = "select * from province";
    private static final String SELECT_PROVINCE_BY_ID = "select idProvince,ProvinceName from province where idProvince =?";


    @Override
    public List<Province> findAll() throws SQLException {
        List<Province> provinces = new ArrayList<>();
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCE);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int idProvince = rs.getInt("IdProvince");
            String provinceName = rs.getString("ProvinceName");
            provinces.add(new Province(idProvince, provinceName));
        }
        disConnection();
        return provinces;
    }

    @Override
    public Province findById(int id) throws SQLException {
        Province province = null;
        setConnection();
        String query = "{CALL get_province_by_id(?)}";
        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, id);

        ResultSet rs = callableStatement.executeQuery();

        while (rs.next()) {
            String provinceName = rs.getString("ProvinceName");
            province = new Province(id, provinceName);

        }
        disConnection();
        return province;
    }

    @Override
    public boolean save(Province object) throws Exception {
        setConnection();
        String SAVE_SQL = "INSERT INTO province " + "  (ProvinceName) VALUES " + " (?);";
        PreparedStatement st;
        st = prepareStatement(object, SAVE_SQL);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public boolean update(Province object) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Province object) throws Exception {
        setConnection();
        String DELETE_SQL = "delete from province where idProvince = ?;";
        PreparedStatement st = connection.prepareStatement(DELETE_SQL);
        st.setInt(1, object.getIdProvince());
        boolean rt = st.executeUpdate() > 0;
        disConnection();
        return rt;
    }

    private PreparedStatement prepareStatement(Province object, String SAVE_SQL) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SAVE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getProvinceName());
        return st;
    }

    public void insertProvinceStore(Province province) throws SQLException {
        String query = "{CALL insert_province(?)}";
        setConnection();
        CallableStatement callableStatement = connection.prepareCall(query);

        callableStatement.setString(1, province.getProvinceName());

        System.out.println(callableStatement);

        callableStatement.executeUpdate();

        disConnection();

    }

    public List<Province> selectAllProvince() throws SQLException {
        List<Province> provinces = new ArrayList<>();
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCE);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            int id = rs.getInt("idProvince");
            String provinceName = rs.getString("ProvinceName");
            provinces.add(new Province(id, provinceName));
        }
        disConnection();
        return provinces;
    }


    public Province selectProvince(int id) throws SQLException {
        Province province = null;
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        ResultSet rs = preparedStatement.executeQuery();

        // Step 4: Process the ResultSet object.
        while (rs.next()) {
            String provinceName = rs.getString("ProvinceName");
            province = new Province(id, provinceName);
        }
        disConnection();
        return province;
    }
}
