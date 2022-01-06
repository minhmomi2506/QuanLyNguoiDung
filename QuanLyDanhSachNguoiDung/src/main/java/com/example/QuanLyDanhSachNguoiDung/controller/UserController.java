package com.example.QuanLyDanhSachNguoiDung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUserHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;
import com.example.QuanLyDanhSachNguoiDung.service.MyUserDetail;
import com.example.QuanLyDanhSachNguoiDung.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
//	@Autowired
//	private TokenAuthenticationService tokenAuthen;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	/* GET LOGGED IN USER INFO */
	public User getLoggedInUser(Authentication authentication) {
		MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
		String username = userDetails.getUsername();
		User user = userRepo.findUserByUsername(username);
		return user;
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/aaa")
	public String aaa() {
		return "aaa";
	}

	/* REGISTER */
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		try {
			if (userRepo.existsByUsername(user.getUsername())) {
				return "this username has already been used!";
			} else {
				return userService.register(user);
			}
		} catch (Exception e) {
			return "Something wrong happened!";
		}
	}

	/* GET ALL USERS */
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	/* FIND USER BY ID */
	@GetMapping("/findUserById/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	/* GET ALL USERS BY NAME */
	@GetMapping("/getAllUsersByStr/{str}")
	public List<User> getAllUsersByFullName(@PathVariable String str) {
		return userService.getAllUsersByString(str);
	}

	/* EDIT USER */
	@PutMapping("/editUser/{id}")
	public String editUser(@PathVariable Long id, @RequestBody EditUserHistory editUserHistory,
			Authentication authentication) {
		User user = getLoggedInUser(authentication);
		return userService.editUserInformation(id, editUserHistory, user);
	}

	/* DELETE USER */
	@DeleteMapping("/deleteUser/{id}")
	public List<User> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	/* GET ALL UNITS EXCEPT THE ONE BELONGS TO THE USER BEING EDITED */
	@GetMapping("/getAllExcept1/{id}")
	public List<Unit> getAllExcept1(@PathVariable Long id) {
		return userService.getAllExcept1(id);
	}

}
