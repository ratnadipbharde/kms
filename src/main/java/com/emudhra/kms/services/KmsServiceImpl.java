package com.emudhra.kms.services;
import com.emudhra.kms.dto.KmsDataDto;
import com.emudhra.kms.model.KmsData;
import com.emudhra.kms.repository.KmsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KmsServiceImpl implements KmsService{

    @Autowired
    KmsRepo kmsRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<KmsData>> getAllKmsDataFromDB() {
        List<KmsData> kmsData = kmsRepo.findAll();
        return ResponseEntity.ok(kmsData);
    }

    @Override
    public ResponseEntity<String> saveDataInDB(KmsDataDto kmsDataDto) {
        KmsData kmsData=modelMapper.map(kmsDataDto,KmsData.class);
        try {
            kmsRepo.save(kmsData);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save data: " + e.getMessage());
        }
    }

}
