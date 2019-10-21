package com.celebration.demo.repository;

import com.celebration.demo.model.entity.UserLocate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Repository
public interface UserLocateRepository extends JpaRepository<UserLocate, String> {
    
    List<UserLocate> findAllByLongitudeBetweenAndLatitudeBetween(Float swLongitude, Float neLongitude, Float swLatitude, Float neLatitude);
}
