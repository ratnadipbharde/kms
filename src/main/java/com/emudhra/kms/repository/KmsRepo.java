package com.emudhra.kms.repository;

import com.emudhra.kms.model.KmsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KmsRepo extends JpaRepository<KmsData, Integer> {
}
