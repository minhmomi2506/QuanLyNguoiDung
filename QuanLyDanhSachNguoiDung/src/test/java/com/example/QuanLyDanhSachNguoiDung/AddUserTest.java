package com.example.QuanLyDanhSachNguoiDung;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.RoleRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AddUserTest {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Test
	public void testCreateUser() {
		Role role = roleRepo.findRoleByRoleName("USER");
		Long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		User user = new User();
		user.setId(3L);
		user.setUsername("nhatanh");
		user.setPassword("nhatanh");
		user.setFullName("Phạm Vũ Nhật Anh");
		user.setCreateDate(date);
		user.setAddress("Bạch Đằng, Hà Nội");
		user.setRole(role);
		userRepo.save(user);
		assertThat(user.getId() > 0);
	}
}
