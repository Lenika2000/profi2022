package com.example.profi.services;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.Participant;
import org.springframework.http.ResponseEntity;

public interface ParticipantServiceInterface {
    ResponseEntity<Long> addParticipant(Long id, Participant participant) throws RequestException;

    ResponseEntity<Long> deleteParticipant(Long promoId, Long participantId) throws RequestException;
}
