package com.example.QuanLyDanhSachNguoiDung.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyDanhSachNguoiDung.entity.Unit;

@Repository
public interface UnitRepo extends JpaRepository<Unit, Long> {

	Unit findUnitById(Long unitId);
	
	List<Unit> findByFatherUnit(Unit unit);

}
