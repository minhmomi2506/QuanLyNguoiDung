package com.example.QuanLyDanhSachNguoiDung.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table
@Data
public class EditUnitHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String unitIdEdit;

	@Column
	private String unitNameEdit;

	@Column
	private String unitDescriptionEdit;

	@Column
	private Date updateDate;

	@JsonBackReference(value = "unit-edit")
	@ManyToOne
	@JoinColumn(name = "unit_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Unit unit;

//	@JsonBackReference(value="unitEdit-unit")
//	@ManyToOne
//	@JoinColumn(name = "unit_father_id")
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private Unit fatherUnit;
}
