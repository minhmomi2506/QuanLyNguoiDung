//package com.example.QuanLyDanhSachNguoiDung.entity;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import lombok.extern.slf4j.Slf4j;
//
//@Aspect
//@Component
//@Slf4j
//public class Log {
//    @After("execution(* com.example.QuanLyDanhSachNguoiDung.service.UserService.*(..))")
//    public void logAfter(JoinPoint joinPoint) {
//        System.out.println("after method: " + joinPoint.getSignature().getName());
//        log.info("This is AOP example");
//    }
//    //
//    // @AfterReturning(pointcut = "execution(* com.example.QuanLyDanhSachNguoiDung.service.UserService.getAll(..))",
//    // returning = "result")
//    // public void logBefore(JoinPoint joinPoint, Object result) {
//    // System.out.println("before method: " + joinPoint.getSignature().getName());
//    // log.info("List users:" + result);
//    // }
//
//    @AfterThrowing(pointcut = "execution(* com.example.QuanLyDanhSachNguoiDung.service.UserService.*(..))",
//                    throwing = "error")
//    public void logThrow(JoinPoint joinPoint, Exception error) {
//        log.info("Exception is: " + error);
//        System.out.println("Exception is: " + error);
//    }
//}
