package com.example.profi.services;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.*;
import com.example.profi.repo.ParticipantRepository;
import com.example.profi.repo.PrizeRepository;
import com.example.profi.repo.PromotionsRepository;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PromotionService {

    private final PromotionsRepository promotionsRepository;
    private final ParticipantRepository participantRepository;
    private final PrizeRepository prizeRepository;

    public PromotionService(PromotionsRepository promotionsRepository, ParticipantRepository participantRepository, PrizeRepository prizeRepository) {
        this.promotionsRepository = promotionsRepository;
        this.participantRepository = participantRepository;
        this.prizeRepository = prizeRepository;
    }

    public ResponseEntity<Long> createPromotion(ShortInfAboutPromotion newPromotion) {
        Promotion createdPromotion = promotionsRepository.save(new Promotion(0L, newPromotion.getName(), newPromotion.getDescription()));
        return ResponseEntity.status(201).body(createdPromotion.getId());
    }

    public ResponseEntity<List<ShortInfAboutPromotion>> getAllPromotions() throws RequestException {
        List<Promotion> promotions = promotionsRepository.findAll();
        if (promotions.isEmpty()) {
            throw new RequestException("Промоакции не найдены", 404);
        } else {
            List<ShortInfAboutPromotion> shortInfAboutPromotions = getShortInf(promotions);
            return ResponseEntity.status(200).body(shortInfAboutPromotions);
        }
    }

    public List<ShortInfAboutPromotion> getShortInf(List<Promotion> promotions) {
        List<ShortInfAboutPromotion> shortInfAboutPromotions = new ArrayList<>();
        promotions.forEach((promotion -> {
            shortInfAboutPromotions.add(new ShortInfAboutPromotion(promotion.getId(), promotion.getName(), promotion.getDescription()));
        }));
        return shortInfAboutPromotions;
    }

    public ResponseEntity<Promotion> getPromotion(Long id) throws RequestException {
        boolean isFound = promotionsRepository.existsById(id);
        if (isFound) {
            Promotion promotion = promotionsRepository.findById(id).get();
            return ResponseEntity.status(200).body(promotion);
        } else {
            throw new RequestException("Промоакция с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Promotion> updatePromotion(ShortInfAboutPromotion shortInfAboutPromotion, Long id) throws RequestException {
        boolean isFound = promotionsRepository.existsById(id);
        if (isFound) {
            Promotion promotion = promotionsRepository.findById(id).get();
            if (shortInfAboutPromotion.getName() != null) promotion.setName(shortInfAboutPromotion.getName());
            promotion.setDescription(shortInfAboutPromotion.getDescription());
            promotionsRepository.save(promotion);
            return ResponseEntity.status(200).body(promotion);
        } else {
            throw new RequestException("Промоакция с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> deletePromotion(Long id) throws RequestException {
        boolean isFound = promotionsRepository.existsById(id);
        if (isFound) {
            promotionsRepository.deleteById(id);
            return ResponseEntity.status(200).body(id);
        } else {
            throw new RequestException("Промоакция с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> addParticipant(Long id, Participant participant) throws RequestException {
        boolean isFound = promotionsRepository.existsById(id);
        if (isFound) {
            Promotion promotion = promotionsRepository.findById(id).get();
            Participant createdParticipant = participantRepository.save(new Participant(0L, participant.getName()));
            Set<Participant> participants = promotion.getParticipants();
            participants.add(createdParticipant);
            promotion.setParticipants(participants);
            promotionsRepository.save(promotion);
            return ResponseEntity.status(201).body(createdParticipant.getId());
        } else {
            throw new RequestException("Промоакция с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> deleteParticipant(Long promoId, Long participantId) throws RequestException {
        boolean isFound = promotionsRepository.existsById(promoId);
        if (isFound) {
            if (participantRepository.existsById(participantId)) {
                Promotion promotion = promotionsRepository.findById(promoId).get();
                Participant participant = participantRepository.findById(participantId).get();
                Set<Participant> participants = promotion.getParticipants();
                participants.remove(participant);
                promotion.setParticipants(participants);
                promotionsRepository.save(promotion);
                participantRepository.deleteById(participantId);
                return ResponseEntity.status(200).body(participantId);
            } else {
                throw new RequestException("Участник с id " + participantId + " не найден", 404);
            }
        } else {
            throw new RequestException("Промоакция с id " + promoId + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> addPrize(Long id, Prize prize) throws RequestException {
        boolean isFound = promotionsRepository.existsById(id);
        if (isFound) {
            Promotion promotion = promotionsRepository.findById(id).get();
            Prize createdPrize = prizeRepository.save(new Prize(0L, prize.getDescription()));
            Set<Prize> prizes = promotion.getPrizes();
            prizes.add(createdPrize);
            promotion.setPrizes(prizes);
            promotionsRepository.save(promotion);
            return ResponseEntity.status(201).body(createdPrize.getId());
        } else {
            throw new RequestException("Промоакция с id " + id + " не найдена", 404);
        }
    }

    public ResponseEntity<Long> deletePrize(Long promoId, Long prizeId) throws RequestException {
        boolean isFound = promotionsRepository.existsById(promoId);
        if (isFound) {
            if (prizeRepository.existsById(prizeId)) {
                Promotion promotion = promotionsRepository.findById(promoId).get();
                Prize prize = prizeRepository.findById(prizeId).get();
                Set<Prize> prizes = promotion.getPrizes();
                prizes.remove(prize);
                promotion.setPrizes(prizes);
                promotionsRepository.save(promotion);
                prizeRepository.deleteById(prizeId);
                return ResponseEntity.status(200).body(prizeId);
            } else {
                throw new RequestException("Приз с id " + prizeId + " не найден", 404);
            }
        } else {
            throw new RequestException("Промоакция с id " + promoId + " не найдена", 404);
        }
    }

    public ResponseEntity<List<Result>> raffle(Long promoId) throws RequestException {
        boolean isFound = promotionsRepository.existsById(promoId);
        if (isFound) {
            Promotion promotion = promotionsRepository.findById(promoId).get();
            Set<Participant> participants = promotion.getParticipants();
            Set<Prize> prizes = promotion.getPrizes();
            if (participants.size() == prizes.size()) {
                Iterator<Participant> participantsIterator = participants.iterator();
                Iterator<Prize> prizesIterator = prizes.iterator();
                List<Result> result = new ArrayList<Result>();
                while (participantsIterator.hasNext()) {
                    result.add(new Result(participantsIterator.next(), prizesIterator.next()));
                }
                return ResponseEntity.status(200).body(result);
            } else {
                throw new RequestException("Невозможно провести розыгрыш", 409);
            }
        } else {
            throw new RequestException("Промоакция с id " + promoId + " не найдена", 404);
        }
    }
}
