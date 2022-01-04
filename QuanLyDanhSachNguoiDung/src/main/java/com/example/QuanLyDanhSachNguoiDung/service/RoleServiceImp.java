package com.example.QuanLyDanhSachNguoiDung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.repo.RoleRepo;

@Component
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleRepo roleRepo;

	/* GET ALL ROLES */
	@Override
	public List<Role> getAll() {
		return roleRepo.findAll();
	}

}
