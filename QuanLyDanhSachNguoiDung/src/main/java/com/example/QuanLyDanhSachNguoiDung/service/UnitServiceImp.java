package com.example.QuanLyDanhSachNguoiDung.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUnitHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.EditUnitHistoryRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UnitRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;

@Component
public class UnitServiceImp implements UnitService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UnitRepo unitRepo;
	
	@Autowired
	private EditUnitHistoryRepo editUnitHistoryRepo;
	
//	unitRepo.save(unit);
//	long millis = System.currentTimeMillis();
//	Date date = new Date(millis);
//	unit.setCreateDate(date);
//	Unit unit1 = unitRepo.findUnitById(fatherUnitId);
//	unit.setFatherUnit(unit1);
//	unitRepo.save(unit);
//	unit.setUnitId("UNIT"+unit.getId());
//	unitRepo.save(unit);
//	return "add unit successfully!";
	
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

	/*GET ALL UNITS*/
	@Override
	public List<Unit> getAll() {
		// TODO Auto-generated method stub
		return unitRepo.findAll();
	}
	
	/*FIND UNIT BY ID*/
	@Override
	public Unit fintUnitById(Long id) {
		// TODO Auto-generated method stub
		return unitRepo.findUnitById(id);
	}
	
	/*DELETE UNIT*/
	@Override
	public List<Unit> deleteUnit(Long id) {
		Unit unit = unitRepo.findUnitById(id);
		Unit fatherUnit = unit.getFatherUnit();
//		List<Unit> units = unitRepo.findByFatherUnit(fatherUnit);
		if(fatherUnit == null) {
			List<User> users1 = userRepo.findByUnit(unit);
			for(User user1 : users1) {
				user1.setUnit(null);
				userRepo.save(user1);
			}
			List<Unit> units = unitRepo.findByFatherUnit(unit);
			for(Unit childUnit : units) {
				childUnit.setFatherUnit(null);
				unitRepo.save(childUnit);
			}
		}
		else {
			List<Unit> units = unitRepo.findByFatherUnit(unit);
			for(Unit childUnit : units) {
				childUnit.setFatherUnit(fatherUnit);
				List<User> users = userRepo.findByUnit(unit);
				for(User user: users) {
					user.setUnit(fatherUnit);
				}
			}
		}
		unitRepo.deleteById(id);
		return unitRepo.findAll();
	}
	
	/*EDIT UNIT*/
	@Override
	public String editUnit(Long id, EditUnitHistory editUnitHistory) {
		Unit unit1 = unitRepo.findUnitById(id);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		editUnitHistory.setUnit(unit1);
		editUnitHistory.setUpdateDate(date);
		editUnitHistoryRepo.save(editUnitHistory);
		unit1.setUnitId(editUnitHistory.getUnitIdEdit());
		unit1.setUnitName(editUnitHistory.getUnitNameEdit());
		unit1.setDescription(editUnitHistory.getUnitDescriptionEdit());
		unitRepo.save(unit1);
		// TODO Auto-generated method stub
		return "Edit unit successfully!";
	}

}
