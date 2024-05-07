package com.emudhra.kms.services;

import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.model.KmsData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KmsService {

    ResponseEntity<List<KmsData>> getAllKmsDataFromDB();

    ResponseEntity<String> saveDataInDB(KmsDataDto kmsDataDto);

    ResponseEntity<String> updateKmsDataInDB(KmsDataDto kmsDataDto);
}

