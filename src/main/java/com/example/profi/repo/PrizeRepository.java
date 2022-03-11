package com.example.profi.repo;

import com.example.profi.model.Prize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository extends CrudRepository<Prize, Long> {
}
