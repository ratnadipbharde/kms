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
public class JwtUtility {

    final String SIGNATURE = "ratnadip";

    @Autowired
	ModelMapper modelMapper;

    @Autowired
    UserLoginDto userLoginDto;


    public String generateToken(UserLoginDto userLogInDto) {
        System.out.println(userLogInDto);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userName", userLogInDto.getUserName());
        claims.put("password", userLogInDto.getPassword());
        return Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS256, SIGNATURE).compact();
    }

    public UserLoginDto decodeToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token).getBody();
        System.out.println("claims is : " + claims);
		//userLoginDto=modelMapper.map(claims, UserLoginDto.class);
		//ModelMapper modelMapper1 = new ModelMapper();
        //userLoginDto=  modelMapper1.map(claims, UserLoginDto.class);
        UserLoginDto userLoginDto1 = new UserLoginDto();
        userLoginDto1.setUserName((String) claims.get("userName"));
        userLoginDto1.setPassword((String) claims.get("password"));
        return userLoginDto1;
    }


}
