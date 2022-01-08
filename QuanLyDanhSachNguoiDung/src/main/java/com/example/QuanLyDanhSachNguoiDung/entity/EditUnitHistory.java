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

import lombok.Data;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL 
 */
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

//	@JsonBackReference(value = "unit-edit")
	@ManyToOne
	@JoinColumn(name = "unit_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Unit unit;
	
	@Column
	private String updateUserName;
	
	@Column
	private String unitFather;

//	@JsonBackReference(value="unitEdit-unit")
//	@ManyToOne
//	@JoinColumn(name = "unit_father_id")
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private Unit fatherUnit;
}
