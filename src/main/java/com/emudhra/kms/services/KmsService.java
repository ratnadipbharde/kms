package com.emudhra.kms.services;

import com.emudhra.kms.dto.AddRemarkDto;
import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.dto.RemarkDto;
import com.emudhra.kms.model.KmsData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KmsService {

    ResponseEntity<List<KmsDataDto>> getAllKmsDataFromDB();

    ResponseEntity<String> saveKmsDataInDB(KmsDataDto kmsDataDto);

    ResponseEntity<String> addRemarkInProject(AddRemarkDto addRemarkDto);
}

