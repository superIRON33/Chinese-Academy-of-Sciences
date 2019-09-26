package com.celebration.demo.repository;

import com.celebration.demo.model.entity.Bless;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Repository
public interface StatisticsRepository extends JpaRepository<Bless, String> {
    
    @Modifying
    @Query(value = "select u.institute, sum(b.likes) from bless b inner join user_info u where u.id = b.user_id group by u.institute", nativeQuery = true)
    List<Object> statisticsInstitute();
    
    @Modifying
    @Query(value = "select u.province, sum(b.likes) from bless b inner join user_info u where u.id = b.user_id group by u.province", nativeQuery = true)
    List<Object> statisticsProvince();
}
