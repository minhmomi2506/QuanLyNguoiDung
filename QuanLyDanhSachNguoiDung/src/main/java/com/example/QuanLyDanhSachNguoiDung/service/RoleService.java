package com.example.QuanLyDanhSachNguoiDung.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL 
 */
@Service
public interface RoleService {
	List<Role> getAll();
}
