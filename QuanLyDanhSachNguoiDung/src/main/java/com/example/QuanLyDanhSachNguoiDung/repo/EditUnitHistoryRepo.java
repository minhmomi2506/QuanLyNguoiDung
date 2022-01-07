package com.example.QuanLyDanhSachNguoiDung.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUnitHistory;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL 
 */
@Repository
public interface EditUnitHistoryRepo extends JpaRepository<EditUnitHistory, Long> {
	EditUnitHistory findEditUnitHistoryById(Long id);
}
