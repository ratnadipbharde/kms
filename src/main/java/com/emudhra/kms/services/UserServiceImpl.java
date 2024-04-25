package com.emudhra.kms.services;

import com.emudhra.kms.dto.UserLoginDto;
import com.emudhra.kms.dto.ResponseDto;
import com.emudhra.kms.model.User;
import com.emudhra.kms.repository.UserRepo;
import com.emudhra.kms.utility.JwtUitility;
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
    JwtUitility jwtUitility;

    @Override
    public ResponseEntity<ResponseDto> userLogin(UserLoginDto userLoginDto) {
        ResponseDto responseDto = new ResponseDto();
        User user = null;
        try {
            user = modelMapper.map(userRepo.getUserByUserName(userLoginDto.getUserName()), User.class);
            if (user != null && userLoginDto.getUserName() != null && userLoginDto.getPassword() != null) {
                // User found, proceed with response
                responseDto.setToken(jwtUitility.genrateToken(userLoginDto));
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

public boolean isauthrised(){
return false;
}


}
