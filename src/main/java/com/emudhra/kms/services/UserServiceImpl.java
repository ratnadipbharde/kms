package com.emudhra.kms.services;

import com.emudhra.kms.dto.ResponseDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.LogInResponseDto;
import com.emudhra.kms.model.User;
import com.emudhra.kms.repository.UserRepo;
import com.emudhra.kms.utility.JwtUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtUtility jwtUtility;

    @Override
    public ResponseEntity<LogInResponseDto> userLogin(UserLoginDto userLoginDto) {
        LogInResponseDto responseDto = new LogInResponseDto();
        User user = null;
        try {
            user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            if (user != null && userLoginDto.getUserName() != null && userLoginDto.getPassword() != null) {
                // User found, proceed with response
                responseDto.setToken(jwtUtility.genrateToken(userLoginDto));
                responseDto.setRoles(user.getRole());
                user.setIsLogin(true);
                userRepo.save(user);
                return ResponseEntity.ok(responseDto);
            } else {
                // Invalid credentials, return appropriate response
                responseDto.setError("Invalid credentials");
                return ResponseEntity.badRequest().body(responseDto);
            }
        } catch (Exception e) {
            // Error occurred during user retrieval
            responseDto.setError("invallid credential");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    public boolean isAuthorized(String token) {
        if (token != null) {
            try {
                UserLoginDto userLoginDto = jwtUtility.decodeToken(token);
                User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);

                if (user != null && user.getUserName().equals(userLoginDto.getUserName())) {
                    // Here you might want to compare hashed passwords
                    if (user.getPassword().equals(userLoginDto.getPassword())) {
                        return true;
                    } else {
                        // Log password mismatch
                        System.out.println("Password mismatch for user: " + user.getUserName());
                        return false;
                    }
                } else {
                    // Log user not found
                    System.out.println("User not found for token: " + token);
                    return false;
                }
            } catch (Exception e) {
                // Log decoding exception
                System.out.println("Invalid token: " + token);
                return false;
            }
        } else {
            // Log null token
            System.out.println("Null token provided.");
            return false;
        }
    }


    public ResponseEntity<ResponseDto> getUserInfo(String token) {
        ResponseDto responseDto = new ResponseDto();
        if (isAuthorized(token) && isLogin(token)) {
            UserLoginDto userLoginDto = modelMapper.map(jwtUtility.decodeToken(token), UserLoginDto.class);
            UserDto userDto = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), UserDto.class);
            responseDto.setResponseData(userDto);
            responseDto.setMessage("fetch user data successfully");
            return ResponseEntity.ok(responseDto);
        } else {
            responseDto.setResponseData(null);
            responseDto.setMessage("something went wrong");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @Override
    public ResponseEntity<ResponseDto> userLogout(String token) {
        ResponseDto responseDto=new ResponseDto();
        if (isLogin(token)&&isAuthorized(token)){
            UserLoginDto userLoginDto = modelMapper.map(jwtUtility.decodeToken(token), UserLoginDto.class);
            User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            user.setIsLogin(false);
            userRepo.save(user);
            responseDto.setMessage("User Logout Successfully");
            return ResponseEntity.ok(responseDto);
        }else {
            responseDto.setMessage("something went wrong");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    public boolean isLogin(String token){
        if (isAuthorized(token)){
            UserLoginDto userLoginDto = modelMapper.map(jwtUtility.decodeToken(token), UserLoginDto.class);
            User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            return user.getIsLogin();
        }else {
            return false;
        }
    }


}
