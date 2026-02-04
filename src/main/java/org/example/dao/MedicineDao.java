package org.example.dao;

import org.example.helper.DbConnection;
import org.example.model.Medicine;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MedicineDao {

    public boolean create(Medicine m) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Medicine_Create(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, m.getName());
            cs.setString(2, m.getBarcode());
            cs.setDouble(3, m.getPrice());
            cs.setInt(4, m.getStockQty());
            cs.setDate(5, Date.valueOf(m.getExpiryDate()));
            cs.setInt(6, m.getSupplierId());
            cs.execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(int id, Medicine m) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Medicine_Update(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, id);
            cs.setString(2, m.getName());
            cs.setString(3, m.getBarcode());
            cs.setDouble(4, m.getPrice());
            cs.setInt(5, m.getStockQty());
            cs.setDate(6, Date.valueOf(m.getExpiryDate()));
            cs.setInt(7, m.getSupplierId());
            cs.execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Medicine_Delete(?)}");
            cs.setInt(1, id);
            cs.execute();
            return true;
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && (msg.contains("cannot be deleted")
                    || msg.contains("REFERENCE constraint")
                    || msg.contains("conflicted with the REFERENCE constraint"))) {
                return false;
            }
            throw new RuntimeException(e);
        }
    }


    public List<Medicine> getAll() {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Medicine_GetAll}");
            ResultSet rs = cs.executeQuery();

            List<Medicine> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Medicine(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Barcode"),
                        rs.getDouble("Price"),
                        rs.getInt("StockQty"),
                        rs.getDate("ExpiryDate").toLocalDate(),
                        rs.getInt("SupplierId")
                ));
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Medicine getById(int id) {
        try (Connection conn = DbConnection.connect()) {
            CallableStatement cs = conn.prepareCall("{call dbo.usp_Medicine_GetById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return new Medicine(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Barcode"),
                        rs.getDouble("Price"),
                        rs.getInt("StockQty"),
                        rs.getDate("ExpiryDate").toLocalDate(),
                        rs.getInt("SupplierId")
                );
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
