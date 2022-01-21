package com.example.QuanLyDanhSachNguoiDung.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @date 2022-01-06 - CREATE NEW
 *
 * @author MinhHL
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("fatherUnit")
    @JoinColumn(name = "unit_father_id")
    private Unit fatherUnit;
}
