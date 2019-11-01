package com.celebration.demo.repository;

import com.celebration.demo.model.entity.Bless;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.Optional;

public interface BlessRepository extends JpaRepository<Bless, String> {

    Page<Bless> findAllBy(Pageable pageable);

    @Override
    <S extends Bless> S saveAndFlush(S s);
    
    @Query(value = "SELECT COUNT(DISTINCT user_id) FROM bless", nativeQuery = true)
    Integer countUserInfo();

    @Override
    Optional<Bless> findById(String s);
}
