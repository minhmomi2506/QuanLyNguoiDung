package com.example.QuanLyDanhSachNguoiDung.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUnitHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;

@Service
public interface UnitService {
	List<Unit> getAll();

	String addUnit(Unit unit, Long fatherUnitId);
	
	List<Unit> deleteUnit(Long id);
	
	Unit fintUnitById(Long id);
	
	String editUnit(Long id, EditUnitHistory editUnitHistory);
}
