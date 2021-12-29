package com.example.QuanLyDanhSachNguoiDung.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findUserByUsername(String username);

	User findUserById(Long id);

	User findByUsernameAndPassword(String username, String password);
	
	List<User> findByUnit(Unit unit);
	
	Boolean existsByUsername(String username);

}
