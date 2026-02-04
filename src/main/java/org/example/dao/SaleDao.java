package org.example.dao;

import org.example.helper.DbConnection;
import org.example.model.Sale;
import org.example.model.SaleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class SaleDao {

    public int createSale(Sale sale, List<SaleItem> items) {
        Connection conn = null;

        try {
            conn = DbConnection.connect();
            conn.setAutoCommit(false);

            int saleId;

            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Sales(SaleDate, UserId, TotalAmount) VALUES(?, ?, ?); " +
                            "SELECT SCOPE_IDENTITY() AS NewId;"
            )) {
                ps.setTimestamp(1, Timestamp.valueOf(sale.getSaleDate()));
                ps.setInt(2, sale.getUserId());
                ps.setDouble(3, sale.getTotalAmount());

                ResultSet rs = ps.executeQuery();
                rs.next();
                saleId = rs.getBigDecimal("NewId").intValue();
            }

            for (SaleItem it : items) {

                try (PreparedStatement psStock = conn.prepareStatement(
                        "UPDATE Medicines " +
                                "SET StockQty = StockQty - ? " +
                                "WHERE Id = ? AND StockQty >= ?"
                )) {
                    psStock.setInt(1, it.getQty());
                    psStock.setInt(2, it.getMedicineId());
                    psStock.setInt(3, it.getQty());

                    int affected = psStock.executeUpdate();
                    if (affected == 0) {
                        throw new RuntimeException("Not enough stock for MedicineId=" + it.getMedicineId());
                    }
                }

                try (PreparedStatement psItem = conn.prepareStatement(
                        "INSERT INTO SaleItems(SaleId, MedicineId, Qty, UnitPrice) VALUES(?, ?, ?, ?)"
                )) {
                    psItem.setInt(1, saleId);
                    psItem.setInt(2, it.getMedicineId());
                    psItem.setInt(3, it.getQty());
                    psItem.setDouble(4, it.getUnitPrice());
                    psItem.executeUpdate();
                }
            }

            conn.commit();
            return saleId;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ignored) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {
            }
        }
    }
}
