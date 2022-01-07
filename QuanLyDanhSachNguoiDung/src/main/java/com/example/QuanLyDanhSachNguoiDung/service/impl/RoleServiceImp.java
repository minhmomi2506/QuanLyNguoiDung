package com.example.QuanLyDanhSachNguoiDung.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.repo.RoleRepo;
import com.example.QuanLyDanhSachNguoiDung.service.RoleService;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
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
