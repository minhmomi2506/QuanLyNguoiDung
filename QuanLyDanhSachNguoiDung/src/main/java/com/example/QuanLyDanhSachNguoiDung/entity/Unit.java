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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
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

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "unit_father_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Unit fatherUnit;
}
