package com.example.profi.services;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.Prize;
import org.springframework.http.ResponseEntity;

public interface PrizeServiceInterface {
    ResponseEntity<Long> addPrize(Long id, Prize prize) throws RequestException;

    ResponseEntity<Long> deletePrize(Long promoId, Long prizeId) throws RequestException;
}
