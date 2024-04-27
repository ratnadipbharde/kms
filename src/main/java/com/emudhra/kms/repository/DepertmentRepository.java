package com.emudhra.kms.repository;

import com.emudhra.kms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepertmentRepository extends JpaRepository<Department,Integer> {
    Department findDepartmentByDepartmentName(String departmentName);
}
