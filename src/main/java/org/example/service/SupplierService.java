package org.example.service;

import org.example.dao.SupplierDao;
import org.example.model.Supplier;

import java.util.List;

public class SupplierService {

    private final SupplierDao supplierDao = new SupplierDao();

    public boolean create(Supplier supplier) {
        return supplierDao.create(supplier);
    }

    public boolean update(int id, Supplier supplier) {
        return supplierDao.update(id, supplier);
    }

    public boolean delete(int id) {
        return supplierDao.delete(id);
    }

    public List<Supplier> getAll() {
        return supplierDao.getAll();
    }
}
