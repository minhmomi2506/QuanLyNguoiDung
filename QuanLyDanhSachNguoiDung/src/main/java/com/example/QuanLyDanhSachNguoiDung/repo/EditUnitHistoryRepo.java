package com.example.QuanLyDanhSachNguoiDung.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUnitHistory;

@Repository
public interface EditUnitHistoryRepo extends JpaRepository<EditUnitHistory, Long> {
	EditUnitHistory findEditUnitHistoryById(Long id);
}
