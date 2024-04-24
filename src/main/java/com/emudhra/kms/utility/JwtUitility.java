package com.emudhra.kms.utility;

import java.util.HashMap;

import com.emudhra.kms.dto.UserLoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUitility {

	final String SIGNATURE = "ratnadip";

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserLoginDto userLogInDto;


	public String genrateToken(UserLoginDto userLogInDto) {
		System.out.println(userLogInDto);
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("userName", userLogInDto.getUserName());
		claims.put("password", userLogInDto.getPassword());
		return Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS256, SIGNATURE).compact();
	}

	public UserLoginDto decodeToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token).getBody();
		return modelMapper.map(claims, UserLoginDto.class);
	}
}
