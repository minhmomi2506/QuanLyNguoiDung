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

import com.example.QuanLyDanhSachNguoiDung.entity.EditUnitHistory;
import com.example.QuanLyDanhSachNguoiDung.entity.Unit;
import com.example.QuanLyDanhSachNguoiDung.service.UnitService;

@RestController
@CrossOrigin(origins = "*")
public class UnitController {
	@Autowired
	private UnitService unitService;
	
	/*ADD UNIT*/
	@PostMapping("/addUnit/{fatherUnitId}")
	public String addUnit(@RequestBody Unit unit, @PathVariable Long fatherUnitId) {
		return unitService.addUnit(unit, fatherUnitId);
	}
	
	/*GET ALL UNITS*/
	@GetMapping("/getAllUnits")
	public List<Unit> getAllUnits(){
		return unitService.getAll();
	}
	
	/*FIND UNIT BY ID*/
	@GetMapping("/findUnitById/{id}")
	public Unit findUnitById(@PathVariable Long id) {
		return unitService.fintUnitById(id);
	}
	
	/*EDIT UNIT*/
	@PutMapping("/editUnit/{id}")
	public String editUnit(@PathVariable Long id, @RequestBody EditUnitHistory editUnitHistory) {
		return unitService.editUnit(id, editUnitHistory);
	}
	
	/*DELETE UNIT*/
	@DeleteMapping("/deleteUnit/{id}")
	public List<Unit> deleteUnit(@PathVariable Long id){
		return unitService.deleteUnit(id);
	}
}
