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

import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;
import com.example.QuanLyDanhSachNguoiDung.service.MyUserDetail;
import com.example.QuanLyDanhSachNguoiDung.service.UserService;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	/**
	 * @param authentication
	 * @return
	 */
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

	/**
	 * @param user
	 * @return
	 */
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

	/**
	 * @return
	 */
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/findUserById/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	/**
	 * @param str
	 * @return
	 */
	@GetMapping("/getAllUsersByStr/{str}")
	public List<User> getAllUsersByFullName(@PathVariable String str) {
		return userService.getAllUsersByString(str);
	}

	/**
	 * @param id
	 * @param editUserHistory
	 * @param authentication
	 * @return
	 */
	@PutMapping("/editUser/{id}")
	public String editUser(@PathVariable Long id, @RequestBody User user,
			Authentication authentication) {
		User user1 = getLoggedInUser(authentication);
		return userService.editUserInformation(id, user, user1);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteUser/{id}")
	public List<User> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/getAllExcept1/{id}")
	public List<Unit> getAllExcept1(@PathVariable Long id) {
		return userService.getAllExcept1(id);
	}

}
