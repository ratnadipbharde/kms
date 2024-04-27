package com.emudhra.kms.services;

import com.emudhra.kms.dto.ResponseDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.LogInResponseDto;
import com.emudhra.kms.model.Department;
import com.emudhra.kms.model.Role;
import com.emudhra.kms.model.User;
import com.emudhra.kms.repository.DepertmentRepository;
import com.emudhra.kms.repository.RoleRepository;
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

    @Autowired
    DepertmentRepository depertmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<LogInResponseDto> userLogin(UserLoginDto userLoginDto) {
        LogInResponseDto responseDto = new LogInResponseDto();

        try {
            User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            if (user != null && userLoginDto.getUserName() != null && userLoginDto.getPassword() != null) {
                // User found, proceed with response
                responseDto.setToken(jwtUtility.genrateToken(userLoginDto));
                responseDto.setRoles(user.getRole().getRoleName());
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
            responseDto.setError("invalid credential");
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
        ResponseDto responseDto = new ResponseDto();
        if (isLogin(token) && isAuthorized(token)) {
            UserLoginDto userLoginDto = modelMapper.map(jwtUtility.decodeToken(token), UserLoginDto.class);
            User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            user.setIsLogin(false);
            userRepo.save(user);
            responseDto.setMessage("User Logout Successfully");
            return ResponseEntity.ok(responseDto);
        } else {
            responseDto.setMessage("something went wrong");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @Override
    public ResponseEntity<ResponseDto> addUserInDatabase(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        ResponseDto responseDto = new ResponseDto();
        if (user.getRole().equals("superadmin")) {
            // Check if the department already exists
            Department existingDepartment = depertmentRepository.findDepartmentByDepartmentName(user.getDepartment().getDepartmentName());
            if (existingDepartment != null) {
                // If department exists, set the existing department in the user
                user.setDepartment(existingDepartment);
            } else {
                // If department doesn't exist, save the department and set it in the user
                Department newDepartment = modelMapper.map(depertmentRepository.save(user.getDepartment()), Department.class);
                user.setDepartment(newDepartment);
            }
            Role existingRole = roleRepository.findRoleByRoleName(user.getRole().getRoleName());
            if (existingRole != null) {
                // If department exists, set the existing department in the user
                user.setRole(existingRole);
            } else {
                // If department doesn't exist, save the department and set it in the user
                Role newRole = modelMapper.map(roleRepository.save(user.getRole()), Role.class);
                user.setRole(newRole);
            }
            userRepo.save(user); // Save the user
            responseDto.setResponseData(user);
            return ResponseEntity.ok(responseDto);
        }else {
            responseDto.setMessage("please login with superadmin credentials");
            return ResponseEntity.ok(responseDto);
        }
    }

    public boolean isLogin(String token) {
        if (isAuthorized(token)) {
            UserLoginDto userLoginDto = modelMapper.map(jwtUtility.decodeToken(token), UserLoginDto.class);
            User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            return user.getIsLogin();
        } else {
            return false;
        }
    }


}
