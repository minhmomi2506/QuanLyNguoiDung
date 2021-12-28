package com.example.QuanLyDanhSachNguoiDung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUserHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;
	
	/*REGISTER*/
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		try {
			return userService.register(user);
		} catch (Exception e) {
			// TODO: handle exception
			return "Something wrong happened!";
		}
	}
	
	/*GET ALL USERS*/
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	
	/*FIND USER BY ID*/
	@GetMapping("/findUserById/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}
	
	/*GET ALL USERS BY NAME*/
	@GetMapping("/getAllUsersByStr/{str}")
	public List<User> getAllUsersByFullName(@PathVariable String str){
		return userService.getAllUsersByString(str);
	}
	
	/*EDIT USER*/
	@PutMapping("/editUser/{id}")
	public String editUser(@PathVariable Long id, @RequestBody EditUserHistory editUserHistory) {
		return userService.editUserInformation(id, editUserHistory);
	}
	
	/*DELETE USER*/
	@DeleteMapping("/deleteUser/{id}")
	public List<User> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
	
}
