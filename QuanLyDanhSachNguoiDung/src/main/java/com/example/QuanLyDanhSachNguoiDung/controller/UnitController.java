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
import com.example.QuanLyDanhSachNguoiDung.service.UnitService;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@RestController
@CrossOrigin(origins = "*")
public class UnitController {
	@Autowired
	private UnitService unitService;

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

	/**
	 * @param unit
	 * @param fatherUnitId
	 * @return
	 */
	@PostMapping("/addUnit/{fatherUnitId}")
	public String addUnit(@RequestBody Unit unit, @PathVariable Long fatherUnitId) {
		return unitService.addUnit(unit, fatherUnitId);
	}

	/**
	 * @return
	 */
	@GetMapping("/getAllUnits")
	public List<Unit> getAllUnits() {
		return unitService.getAll();
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/findUnitById/{id}")
	public Unit findUnitById(@PathVariable Long id) {
		return unitService.fintUnitById(id);
	}

	/**
	 * @param id
	 * @param editUnitHistory
	 * @param authentication
	 * @return
	 */
	@PutMapping("/editUnit/{id}/{fatherUnitId}")
	public String editUnit(@PathVariable Long id, @RequestBody Unit unit,
			Authentication authentication, @PathVariable Long fatherUnitId) {
		User user = getLoggedInUser(authentication);
		return unitService.editUnit(id, unit, user, fatherUnitId);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteUnit/{id}")
	public List<Unit> deleteUnit(@PathVariable Long id) {
		return unitService.deleteUnit(id);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/getAllUnitsExcept1/{id}")
	public List<Unit> getAllExcept2(@PathVariable Long id) {
		return unitService.getAllExcept1(id);
	}
}
