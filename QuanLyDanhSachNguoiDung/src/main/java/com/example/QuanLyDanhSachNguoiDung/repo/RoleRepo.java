package com.example.QuanLyDanhSachNguoiDung.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findRoleById(Long roleId);

	Role findRoleByRoleName(String string);

}
