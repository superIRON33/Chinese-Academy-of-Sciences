package com.celebration.demo.repository;

import com.celebration.demo.model.entity.Commend;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CommendRepository extends JpaRepository<Commend, String> {

    Optional<Commend> findCommendByUserIdAndBlessId(String userId, String blessId);
    
    @Transactional
    @Override
    void delete(Commend commend);

    @Override
    <S extends Commend> S saveAndFlush(S s);
}
