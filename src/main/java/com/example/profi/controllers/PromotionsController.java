package com.example.profi.controllers;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.*;
import com.example.profi.repo.ParticipantRepository;
import com.example.profi.repo.PrizeRepository;
import com.example.profi.repo.PromotionsRepository;
import com.example.profi.services.PromotionServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/promo")
public class PromotionsController {

    private PromotionServiceService promotionService;

    public PromotionsController(PromotionsRepository promotionsRepository, ParticipantRepository participantRepository, PrizeRepository prizeRepository) {
        this.promotionService = new PromotionServiceService(promotionsRepository, participantRepository, prizeRepository);
    }

    @PostMapping
    private ResponseEntity<Long> addPromotion(@RequestBody ShortInfAboutPromotion newPromotion) throws RequestException {
        if (newPromotion.getName() == null) {
            throw new RequestException("Поле name обязательно для заполнения", 400);
        }
        return promotionService.createPromotion(newPromotion);
    }

    @GetMapping
    private ResponseEntity<?> getAllPromotions() throws RequestException {
        return promotionService.getAllPromotions();
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<?> getPromotion(@PathVariable Long id) throws RequestException {
        return promotionService.getPromotion(id);
    }

    @PutMapping(value = "/{id}")
    private ResponseEntity<?> updatePromotion(@PathVariable Long id, @RequestBody ShortInfAboutPromotion note) throws RequestException {
        return promotionService.updatePromotion(note, id);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<?> deletePromotion(@PathVariable Long id) throws RequestException {
        return promotionService.deletePromotion(id);
    }

    @PostMapping(value = "/{id}/participant")
    private ResponseEntity<Long> addParticipant(@PathVariable Long id, @RequestBody Participant participant) throws RequestException {
        if (participant.getName() == null) {
            throw new RequestException("Поле name обязательно для заполнения", 400);
        }
        return promotionService.addParticipant(id, participant);
    }

    @DeleteMapping(value = "/{promoId}/participant/{participantId}")
    private ResponseEntity<?> deleteParticipant(@PathVariable Long promoId, @PathVariable Long participantId) throws RequestException {
        return promotionService.deleteParticipant(promoId, participantId);
    }

    @PostMapping(value = "/{id}/prize")
    private ResponseEntity<Long> addPrize(@PathVariable Long id, @RequestBody Prize prize) throws RequestException {
        if (prize.getDescription() == null) {
            throw new RequestException("Поле description обязательно для заполнения", 400);
        }
        return promotionService.addPrize(id, prize);
    }

    @DeleteMapping(value = "/{promoId}/prize/{prizeId}")
    private ResponseEntity<?> deletePrize(@PathVariable Long promoId, @PathVariable Long prizeId) throws RequestException {
        return promotionService.deletePrize(promoId, prizeId);
    }

    @PostMapping(value = "/{id}/raffle")
    private ResponseEntity<?> raffle(@PathVariable Long id) throws RequestException {
        return promotionService.raffle(id);
    }
}
