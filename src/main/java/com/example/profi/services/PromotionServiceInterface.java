package com.example.profi.services;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.*;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface PromotionServiceInterface {

    ResponseEntity<Long> createPromotion(ShortInfAboutPromotion newPromotion);

    ResponseEntity<List<ShortInfAboutPromotion>> getAllPromotions() throws RequestException;

    List<ShortInfAboutPromotion> getShortInf(List<Promotion> promotions);

    ResponseEntity<Promotion> getPromotion(Long id) throws RequestException;

    ResponseEntity<Promotion> updatePromotion(ShortInfAboutPromotion shortInfAboutPromotion, Long id) throws RequestException;

    ResponseEntity<Long> deletePromotion(Long id) throws RequestException;

    ResponseEntity<List<Result>> raffle(Long promoId) throws RequestException;
}
