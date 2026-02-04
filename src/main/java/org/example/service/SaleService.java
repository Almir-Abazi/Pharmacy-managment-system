package org.example.service;

import org.example.dao.MedicineDao;
import org.example.dao.SaleDao;
import org.example.model.Medicine;
import org.example.model.Sale;
import org.example.model.SaleItem;

import java.util.List;

public class SaleService {

    private final SaleDao saleDao = new SaleDao();
    private final MedicineDao medicineDao = new MedicineDao();

    public int createSale(int userId, List<SaleItem> items) {
        double total = 0;

        for (SaleItem it : items) {
            Medicine m = medicineDao.getById(it.getMedicineId());
            if (m == null) {
                throw new RuntimeException("Medicine not found: " + it.getMedicineId());
            }
            total += it.getLineTotal();
        }

        Sale sale = new Sale(userId, total);
        return saleDao.createSale(sale, items);
    }
}
