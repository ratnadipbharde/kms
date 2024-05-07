package com.emudhra.kms.services;

import com.emudhra.kms.dto.*;
import com.emudhra.kms.dto.LogInResponseDto;
import com.emudhra.kms.dto.ResetPasswordDto;
import com.emudhra.kms.dto.UserDto;
import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.model.Department;
import com.emudhra.kms.model.Role;
import com.emudhra.kms.model.User;
import com.emudhra.kms.repository.DepertmentRepository;
import com.emudhra.kms.repository.RoleRepository;
import com.emudhra.kms.repository.UserRepo;
import com.emudhra.kms.utility.EmailSenderUtility;
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
    EmailSenderUtility emailSenderUtility;

    @Autowired
    DepertmentRepository depertmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<LogInResponseDto> userLogin(UserLoginDto userLoginDto) {
        LogInResponseDto logInResponseDto = new LogInResponseDto();
        try {
            User user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);

            if (userLoginDto.getUserName() != null && userLoginDto.getPassword() != null) {
                // User found, proceed with response
                logInResponseDto.setToken(jwtUtility.generateToken(userLoginDto));
                logInResponseDto.setRoles(user.getRole().getRoleName());
                logInResponseDto.setIsFirstLogin(user.getIsFirstLogin());
                user.setIsLogin(true);
                userRepo.save(user);
                return ResponseEntity.ok(logInResponseDto);
            } else {
                // Invalid credentials, return appropriate response
                logInResponseDto.setError("Invalid credentials");
                return ResponseEntity.badRequest().body(logInResponseDto);
            }

        } catch (Exception e) {
            // Error occurred during user retrieval
            logInResponseDto.setError("invalid credential");
            return ResponseEntity.badRequest().body(logInResponseDto);
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
    public ResponseEntity<ResponseDto> addUserInDatabase(UserDto userDto, String token) {
        User user = modelMapper.map(userDto, User.class);
        ResponseDto responseDto = new ResponseDto();
        UserLoginDto userLoginDto=modelMapper.map(jwtUtility.decodeToken(token),UserLoginDto.class);
        User user1 = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
        if ( user1.getRole().getRoleName().equals("superadmin")) {
        user.setPassword("Pass@123");
        user.setIsLogin(false);
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

            String subject="KMS User Credentials";
            String body="Dear "+user.getFirstName()+",\n"+"We are pleased to inform you that your account has been successfully created in [KMS].\n" +
                    "\n" +
                    "Your login credentials are as follows:\n"+
            "Username : "+user.getUserName()+"\nPassword : "+user.getPassword()+"\n"+
                    "\n" +
                    "For security reasons, we recommend changing your password upon first login. " +
                    "Please follow the steps below to access your account:\n" +
                    "\n" +
                    "1. Visit [System/Platform URL]\n" +
                    "2. Enter your username and temporary password.\n" +
                    "3. Follow the prompts to set up your new password.\n" +
                    "4. Once your password has been changed, you will have full access to your account.\n" +
                    "\n" +
                    "If you have any questions or need further assistance, feel free to contact our support " +
                    "team at [Support Email/Phone Number].\n";
            emailSenderUtility.sendSimpleMail(user.getEmail(),subject,body);
        }else {
            responseDto.setMessage("please login with superadmin credentials");
        }
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<ResponseDto> resetPassword(ResetPasswordDto resetPasswordDto, String token) {
        return null;
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
