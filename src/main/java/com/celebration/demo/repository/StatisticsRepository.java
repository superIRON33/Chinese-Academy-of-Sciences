package com.celebration.demo.repository;

import com.celebration.demo.model.entity.Bless;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Repository
public interface StatisticsRepository extends JpaRepository<Bless, String> {
    
    @Transactional
    @Modifying
    @Query(value = "SELECT u.institute, SUM(b.likes) FROM bless b INNER JOIN user_info u WHERE u.id = b.user_id GROUP BY u.institute ORDER BY SUM(b.likes) DESC", nativeQuery = true)
    List<Object> statisticsInstitute();
    
    @Transactional
    @Modifying
    @Query(value = "SELECT u.province, SUM(b.likes) FROM bless b INNER JOIN user_locate u WHERE u.id = b.user_id GROUP BY u.province ORDER BY SUM(b.likes) DESC", nativeQuery = true)
    List<Object> statisticsProvince();
    
    @Transactional
    @Modifying
    @Query(value = "SELECT u.country, SUM(b.likes) FROM bless b INNER JOIN user_info u WHERE u.id = b.user_id GROUP BY u.country ORDER BY SUM(b.likes) DESC", nativeQuery = true)
    List<Object> statisticsCountry();
    
    @Transactional
    @Modifying
    @Query(value = "SELECT u.province, COUNT(u.province), p.longitude, p.latitude FROM user_locate u INNER JOIN province p WHERE u.longitude BETWEEN :swLongitude AND :neLongitude AND u.latitude BETWEEN :swLatitude AND :neLatitude AND p.name = u.province GROUP BY u.province ORDER BY COUNT(u.province)", nativeQuery = true)
    List<Object> statisticsMapProvince(@Param("swLongitude") Float swLongitude, @Param("neLongitude") Float neLongitude, @Param("swLatitude") Float swLatitude, @Param("neLatitude") Float neLatitude);
    
    @Transactional
    @Modifying
    @Query(value = "SELECT u.city, COUNT(u.city), p.longitude, p.latitude FROM user_locate u INNER JOIN city p WHERE u.longitude BETWEEN :swLongitude AND :neLongitude AND u.latitude BETWEEN :swLatitude AND :neLatitude AND p.name = u.city GROUP BY u.city ORDER BY COUNT(u.city)", nativeQuery = true)
    List<Object> statisticsMapCity(@Param("swLongitude") Float swLongitude, @Param("neLongitude") Float neLongitude, @Param("swLatitude") Float swLatitude, @Param("neLatitude") Float neLatitude);
    
    @Transactional
    @Modifying
    @Query(value = "SELECT u.region, COUNT(u.region), p.longitude, p.latitude FROM user_locate u INNER JOIN region p WHERE u.longitude BETWEEN :swLongitude AND :neLongitude AND u.latitude BETWEEN :swLatitude AND :neLatitude AND p.name = u.region GROUP BY u.region ORDER BY COUNT(u.region)", nativeQuery = true)
    List<Object> statisticsMapRegion(@Param("swLongitude") Float swLongitude, @Param("neLongitude") Float neLongitude, @Param("swLatitude") Float swLatitude, @Param("neLatitude") Float neLatitude);
}
