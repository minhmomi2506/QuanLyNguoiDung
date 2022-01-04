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

	@JsonBackReference(value = "user-role")
	@ManyToOne
	@JoinColumn(name = "role_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Role role;

	@JsonBackReference(value = "user-unit")
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@JsonManagedReference(value = "user-edit")
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<EditUserHistory> editUserHistories;

//	@JsonManagedReference
//	@OneToMany(mappedBy = "createPerson", cascade = { CascadeType.ALL })
//	private List<Unit> units;

//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "role_id") })
//	private Set<Role> roles;

}
