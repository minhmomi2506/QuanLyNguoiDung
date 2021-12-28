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
public class EditUserHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String userFullNameEdit;
	
	@Column
	private String userDescriptionEdit;
	
	@Column
	private String userAddressEdit;
	
	@Column
	private java.sql.Date userDateOfBirthEdit;
	
	@Column
	private Date updateDate;
	
	@JsonBackReference(value="user-edit")
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
}
