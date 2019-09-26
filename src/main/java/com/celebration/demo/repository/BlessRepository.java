package com.celebration.demo.repository;

import com.celebration.demo.model.entity.Bless;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlessRepository extends JpaRepository<Bless, String> {

    Page<Bless> findAllBy(Pageable pageable);

    @Override
    <S extends Bless> S saveAndFlush(S s);

    Integer countAllBy();

    @Override
    Optional<Bless> findById(String s);
}
