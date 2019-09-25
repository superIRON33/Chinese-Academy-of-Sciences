package com.celebration.demo.repository;

import com.celebration.demo.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: wjy
 * @date: 2019/9/25
 * @description:
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    
    @Modifying
    @Query("UPDATE UserInfo userInfo SET userInfo.image = :image WHERE userInfo.id = :id")
    void updateImage(@Param("id") String id, @Param("image") String image);
}
