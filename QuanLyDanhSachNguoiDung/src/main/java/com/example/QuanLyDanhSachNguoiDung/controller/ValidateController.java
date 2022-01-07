package com.example.QuanLyDanhSachNguoiDung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyDanhSachNguoiDung.config.TokenAuthenticationService;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;
import com.example.QuanLyDanhSachNguoiDung.service.UserService;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@RestController
@CrossOrigin(origins = "*")
public class ValidateController {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenAuthenticationService tokenAuthen;

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/validate")
	public String login(@RequestBody User user) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		User user1 = userRepo.findUserByUsername(user.getUsername());
		boolean checkPassword = encode.matches(user.getPassword(), user1.getPassword());
		if (user.getUsername().equals(user1.getUsername()) && checkPassword == true) {
			UserDetails userDetail = userService.loadUserByUsername(user.getUsername());
			return tokenAuthen.generateToken(userDetail);
		} else {
			return "Tài khoản không đúng!";
		}
	}
}
