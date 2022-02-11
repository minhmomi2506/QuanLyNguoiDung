package com.example.QuanLyDanhSachNguoiDung.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConvertStringToDate {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse("2000-06-25");
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        System.out.println(sql);
    }
}
