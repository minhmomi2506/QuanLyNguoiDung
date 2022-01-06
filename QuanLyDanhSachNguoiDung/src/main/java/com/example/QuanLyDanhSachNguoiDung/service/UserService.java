package com.example.QuanLyDanhSachNguoiDung.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.QuanLyDanhSachNguoiDung.entity.EditUserHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Role;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.entity.User;
import com.example.QuanLyDanhSachNguoiDung.repo.EditUserHistoryRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.RoleRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UnitRepo;
import com.example.QuanLyDanhSachNguoiDung.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UnitRepo unitRepo;

	@Autowired
	private EditUserHistoryRepo editUserHistoryRepo;
	
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenFilter;
//	
//	@Autowired
//	private TokenAuthenticationService tokenAuthen;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Khong tim thay");
		} else {
			return new MyUserDetail(user);
		}
	}
	
//	public User getUserFromJwt(HttpServletRequest request) {
//		String jwt = jwtAuthenFilter.getJwtFromRequest(request);
//		String username = tokenAuthen.getUsernameFromToken(jwt);
//		// Lấy thông tin người dùng từ id
//		User user = userRepo.findUserByUsername(username);
//		return user;
//	}

	/* REGISTER */
	public String register(User user) {
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		user.setCreateDate(date);
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String encodedPassword = encode.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Role role = roleRepo.findRoleByRoleName("ROLE_USER");
		user.setRole(role);
		userRepo.save(user);
//		EditUserHistory editUserHistory = new EditUserHistory();
//		editUserHistory.setUser(user);
//		editUserHistory.setUserFullNameEdit(user.getFullName());
//		editUserHistory.setUserAddressEdit(user.getAddress());
//		editUserHistory.setUserDateOfBirthEdit(user.getDateOfBirth());
//		editUserHistory.setUserDescriptionEdit(user.getDescription());
//		editUserHistory.setUpdateDate(date);
//		editUserHistoryRepo.save(editUserHistory);
		return "your acc has been successfully registered!";
	}

	/* GET ALL USERS */
	public List<User> getAll() {
//		User user = getUserFromJwt(request);
//		System.out.println(user);
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
	public String editUserInformation(Long roleId, EditUserHistory editUserHistory, User user) {
		User user1 = userRepo.findUserById(roleId);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		editUserHistory.setUser(user1);
		editUserHistory.setUpdateDate(date);
		editUserHistory.setUpdateUserName(user.getFullName());
		editUserHistoryRepo.save(editUserHistory);
		user1.setFullName(editUserHistory.getUserFullNameEdit());
		user1.setAddress(editUserHistory.getUserAddressEdit());
		user1.setDescription(editUserHistory.getUserDescriptionEdit());
		user1.setDateOfBirth(editUserHistory.getUserDateOfBirthEdit());
		userRepo.save(user1);
		return "Edit user successfully!";
	}

	/* DELETE USER */
	public List<User> deleteUser(Long id) {
		userRepo.deleteById(id);
		return userRepo.findAll();
	}

	/* GET ALL UNITS EXCEPT THE ONE BELONGS TO THE USER BEING EDITED */
	public List<Unit> getAllExcept1(Long id) {
		User user = userRepo.findUserById(id);
		List<Unit> units = unitRepo.findAll();
		units.remove(user.getUnit());
		return units;
	}
}
