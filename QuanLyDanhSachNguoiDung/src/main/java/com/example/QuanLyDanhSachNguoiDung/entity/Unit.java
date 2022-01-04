package com.example.QuanLyDanhSachNguoiDung.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table
@Data
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String unitId;

	@Column
	private String unitName;

	@Column
	private String description;

	@Column
	private Date createDate;

	@JsonManagedReference(value = "user-unit")
	@OneToMany(mappedBy = "unit")
	private List<User> users;

	@JsonManagedReference(value = "unit-edit")
	@OneToMany(mappedBy = "unit", cascade = { CascadeType.ALL })
	private List<EditUnitHistory> editUnitHistories;

	@JsonBackReference(value = "unit-unit")
	@ManyToOne
	@JoinColumn(name = "unit_father_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Unit fatherUnit;
}
