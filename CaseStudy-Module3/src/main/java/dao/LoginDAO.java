package dao;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO extends DAOHelper{

    public Account checkLogin(String user, String password){
        try {
            String query = "select * from account where user ='" + user + "' and password = '" + password + "';";
            setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Account account = new Account(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3));
                return account;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
