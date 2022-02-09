package com.example.QuanLyDanhSachNguoiDung.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUnitHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.EditUnitHistoryRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UnitRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;
import com.example.QuanLyDanhSachNguoiDung.service.UnitService;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@Component
public class UnitServiceImp implements UnitService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private EditUnitHistoryRepo editUnitHistoryRepo;

    @Override
    /* ADD UNIT */
    public String addUnit(Unit unit, Long fatherUnitId) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        unit.setCreateDate(date);
        Unit unit1 = unitRepo.findUnitById(fatherUnitId);
        unit.setFatherUnit(unit1);
        unitRepo.save(unit);
        return "add unit successfully!";
    }

    /* GET ALL UNITS */
    @Cacheable("unitList") // Annotation này sẽ cho phép lưu dữ liệu vào bộ nhớ để tăng tốc độ truy xuất cho lần sau
    @Override
    public List<Unit> getAll() {
        System.out.println("List units");
        return unitRepo.findAll();
    }

    /* DELETE CACHE */
    @CacheEvict("unitList") // Anotation này sẽ xóa cache có tên "unitList" khi phương thức này được gọi
    public void deleteCache() {
        System.out.println("Clear cache unit list");
    }

    /* FIND UNIT BY ID */
    @Override
    public Unit fintUnitById(Long id) {
        return unitRepo.findUnitById(id);
    }

    /* DELETE UNIT */
    @Override
    public List<Unit> deleteUnit(Long id) {
        Unit unit = unitRepo.findUnitById(id);
        Unit fatherUnit = unit.getFatherUnit();
        if (fatherUnit == null) {
            List<User> users1 = userRepo.findByUnit(unit);
            for (User user1 : users1) {
                user1.setUnit(null);
                userRepo.save(user1);
            }
            List<Unit> units = unitRepo.findByFatherUnit(unit);
            for (Unit childUnit : units) {
                childUnit.setFatherUnit(null);
                unitRepo.save(childUnit);
            }
        } else {
            List<Unit> units = unitRepo.findByFatherUnit(unit);
            for (Unit childUnit : units) {
                childUnit.setFatherUnit(fatherUnit);
                List<User> users = userRepo.findByUnit(unit);
                for (User user : users) {
                    user.setUnit(fatherUnit);
                }
            }
        }
        unitRepo.deleteById(id);
        return unitRepo.findAll();
    }

    /* EDIT UNIT */
    @Override
    public String editUnit(Long id, Unit unit, User user, Long fatherUnitId) {
        Unit unit1 = unitRepo.findUnitById(id);
        unit1.setUnitId(unit.getUnitId());
        unit1.setUnitName(unit.getUnitName());
        unit1.setDescription(unit.getDescription());
        Unit fatherUnit = unitRepo.findUnitById(fatherUnitId);
        if (fatherUnit != null && fatherUnit != unit1) {
            unit1.setFatherUnit(fatherUnit);
        } else {
            unit1.setFatherUnit(null);
        }
        unitRepo.save(unit1);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        EditUnitHistory editUnitHistory = new EditUnitHistory();
        editUnitHistory.setUnitIdEdit(unit1.getUnitId());
        editUnitHistory.setUnitDescriptionEdit(unit1.getDescription());
        editUnitHistory.setUnitNameEdit(unit1.getUnitName());
        editUnitHistory.setUnit(unit1);
        editUnitHistory.setUpdateDate(date);
        editUnitHistory.setUnitFather(fatherUnit.getUnitName());
        editUnitHistory.setUpdateUserName(user.getFullName());
        editUnitHistoryRepo.save(editUnitHistory);
        return "Edit unit successfully!";
    }

    /* GET ALL UNITS EXCEPT THE ONE BEING EDITED AND ITS FATHER UNIT */
    @Override
    public List<Unit> getAllExcept1(Long id) {
        Unit unit = unitRepo.findUnitById(id);
        List<Unit> units = unitRepo.findAll();
        units.remove(unit.getFatherUnit());
        return units;
    }

}
