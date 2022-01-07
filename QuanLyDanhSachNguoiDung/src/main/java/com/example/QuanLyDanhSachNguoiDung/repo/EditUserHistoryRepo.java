package com.example.QuanLyDanhSachNguoiDung.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUserHistory;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL 
 */
@Repository
public interface EditUserHistoryRepo extends JpaRepository<EditUserHistory, Long> {

}
