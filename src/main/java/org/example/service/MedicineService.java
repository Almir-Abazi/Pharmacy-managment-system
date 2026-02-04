package org.example.service;

import org.example.dao.MedicineDao;
import org.example.model.Medicine;

import java.util.List;

public class MedicineService {

    private final MedicineDao medicineDao = new MedicineDao();

    public boolean create(Medicine m) {
        return medicineDao.create(m);
    }

    public boolean update(int id, Medicine m) {
        return medicineDao.update(id, m);
    }

    public boolean delete(int id) {
        return medicineDao.delete(id);
    }

    public List<Medicine> getAll() {
        return medicineDao.getAll();
    }

    public Medicine getById(int id) {
        return medicineDao.getById(id);
    }
}
