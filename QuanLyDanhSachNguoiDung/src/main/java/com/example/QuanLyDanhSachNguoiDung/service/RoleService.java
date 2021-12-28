package com.example.QuanLyDanhSachNguoiDung.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;

@Service
public interface RoleService {
	List<Role> getAll();
}
