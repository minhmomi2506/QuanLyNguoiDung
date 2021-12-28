package com.example.QuanLyDanhSachNguoiDung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.service.RoleService;

@RestController
@CrossOrigin(origins = "*")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	/*GET ALL ROLES*/
	@GetMapping("/getAllRoles")
	public List<Role> getAllUsers(){
		return roleService.getAll();
	}
}
