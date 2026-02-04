package org.example.dao;

import org.example.helper.DbConnection;
import org.example.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UserDao {

    public User login(String username, String password) {
        try (Connection conn = DbConnection.connect()) {

            CallableStatement cs = conn.prepareCall("{call dbo.usp_User_Login(?, ?)}");
            cs.setString(1, username);
            cs.setString(2, password);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("Id"),
                        rs.getString("Username"),
                        rs.getInt("RoleId")
                );
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
