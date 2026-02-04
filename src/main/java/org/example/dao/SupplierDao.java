package org.example.dao;

import org.example.helper.DbConnection;
import org.example.model.Supplier;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao {

    public boolean create(Supplier supplier) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Supplier_Create(?, ?, ?)}");
            cs.setString(1, supplier.getName());
            cs.setString(2, supplier.getPhone());
            cs.setString(3, supplier.getAddress());
            cs.execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(int id, Supplier supplier) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Supplier_Update(?, ?, ?, ?)}");
            cs.setInt(1, id);
            cs.setString(2, supplier.getName());
            cs.setString(3, supplier.getPhone());
            cs.setString(4, supplier.getAddress());
            cs.execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Supplier_Delete(?)}");
            cs.setInt(1, id);
            cs.execute();
            return true;
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("cannot be deleted")) {
                return false;
            }
            throw new RuntimeException(e);
        }
    }


    public List<Supplier> getAll() {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Supplier_GetAll}");
            ResultSet rs = cs.executeQuery();

            List<Supplier> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Supplier(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("Address")
                ));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
