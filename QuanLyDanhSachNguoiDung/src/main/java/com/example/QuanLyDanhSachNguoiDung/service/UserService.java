package com.example.QuanLyDanhSachNguoiDung.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUserHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.EditUserHistoryRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;

@Service
public class UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EditUserHistoryRepo editUserHistoryRepo;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepo.findUserByUsername(username);
//		if(user == null) {
//			throw new UsernameNotFoundException("Khong tim thay");
//		}
//		else {
//			return new MyUserDetail(user);
//		}
//	}
	
	/* REGISTER */
	public String register(User user) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		user.setCreateDate(date);
		userRepo.save(user);
//		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//		String encodedPassword = encode.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		userRepo.save(user);
		return "your acc has been successfully registered!";
	}

	/* GET ALL USERS */
	public List<User> getAll() {
		return userRepo.findAll();
	}

	/* GET ALL USERS BY STR */
	public List<User> getAllUsersByString(String str) {
		List<User> users = userRepo.findAll();
		List<User> users1 = new ArrayList<User>();
		for (User user : users) {
			if (user.getFullName().toLowerCase().contains(str.toLowerCase())
					|| user.getDescription().toLowerCase().contains(str.toLowerCase())
					|| user.getAddress().toLowerCase().contains(str.toLowerCase())) {
				users1.add(user);
			}
		}
		return users1;
	}

	/* FIND USER BY ID */
	public User findUserById(Long id) {
		return userRepo.findUserById(id);
	}

	/* EDIT USER */
	public String editUserInformation(Long roleId, EditUserHistory editUserHistory) {
		User user1 = userRepo.findUserById(roleId);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		editUserHistory.setUser(user1);
		editUserHistory.setUpdateDate(date);
		editUserHistoryRepo.save(editUserHistory);
		user1.setFullName(editUserHistory.getUserFullNameEdit());
		user1.setAddress(editUserHistory.getUserAddressEdit());
		user1.setDescription(editUserHistory.getUserDescriptionEdit());
		user1.setDateOfBirth(editUserHistory.getUserDateOfBirthEdit());
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encodedPassword = encoder.encode(password);
//		user.setPassword(encodedPassword);
		userRepo.save(user1);
		return "Edit user successfully!";
	}

	/* DELETE USER */
	public List<User> deleteUser(Long id) {
		userRepo.deleteById(id);
		return userRepo.findAll();
	}
}
