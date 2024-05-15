package com.emudhra.kms.repository;

import com.emudhra.kms.dto.RemarkDto;
import com.emudhra.kms.model.KmsData;
import com.emudhra.kms.model.Remark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemarkRepo extends JpaRepository<Remark, Long> {

}

