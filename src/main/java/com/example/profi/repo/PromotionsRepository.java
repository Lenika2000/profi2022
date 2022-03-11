package com.example.profi.repo;

import com.example.profi.model.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionsRepository extends CrudRepository<Promotion, Long> {
    List<Promotion> findAll();
}
