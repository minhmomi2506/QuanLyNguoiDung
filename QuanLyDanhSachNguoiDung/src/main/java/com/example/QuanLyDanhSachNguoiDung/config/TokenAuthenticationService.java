//package com.example.QuanLyDanhSachNguoiDung.config;
//
//import java.util.Date;
//import java.util.function.Function;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class TokenAuthenticationService {
//	static final long EXPIRATIONTIME = 864_000_000; // 10 days
//	static final String SECRET = "ThisIsASecret";
//	static final String TOKEN_PREFIX = "Bearer";
//	static final String HEADER_STRING = "Authorization";
//
//	public static String addAuthentication(String username) {
//		String JWT = Jwts.builder().setSubject(username)
//				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
//		return (TOKEN_PREFIX + " " + JWT);
//	}
//
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//	
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//	
//	private Claims getAllClaimsFromToken(String token) {
//		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//	}
//}
