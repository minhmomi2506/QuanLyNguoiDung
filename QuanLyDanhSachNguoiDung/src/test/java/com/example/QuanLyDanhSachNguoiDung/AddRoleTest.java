package com.example.QuanLyDanhSachNguoiDung;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.repo.RoleRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AddRoleTest {
	@Autowired
	private RoleRepo roleRepo;
	
	@Test
	public void testCreateUser() {
		Role role = new Role();
		role.setId(3L);
		role.setRoleName("AAA");
		roleRepo.save(role);
		assertThat(role.getId() > 0);
	}
}
