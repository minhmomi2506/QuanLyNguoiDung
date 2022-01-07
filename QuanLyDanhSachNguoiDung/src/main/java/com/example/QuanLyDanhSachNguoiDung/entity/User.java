package com.example.QuanLyDanhSachNguoiDung.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL 
 */
@Entity
@Table(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String fullName;

	@Column
	private String description;

	@Column
	private Date createDate;

	@Column
	private String address;

	@Column
	private java.sql.Date dateOfBirth;

	@JsonBackReference(value = "user-unit")
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@JsonManagedReference(value = "user-edit")
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<EditUserHistory> editUserHistories;

//	@JsonManagedReference(value = "user-user-role")
//	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
//	private List<User_Role> userRoles;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	List<Role> roles = new ArrayList<Role>();

}
