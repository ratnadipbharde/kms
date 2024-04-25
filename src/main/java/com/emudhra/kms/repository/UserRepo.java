package com.emudhra.kms.repository;

import com.emudhra.kms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Object getUserByUserName(String userName);
}
