package com.celebration.demo.repository;

import com.celebration.demo.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description:
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInfo userInfo SET userInfo.image = :image WHERE userInfo.id = :id")
    void updateImage(@Param("id") String id, @Param("image") String image);

    Optional<UserInfo> findUserInfoById(String id);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserInfo userInfo SET userInfo.wechatPNG = :image WHERE userInfo.id = :id")
    void updateWechatPNG(@Param("id") String id, @Param("image") String image);

    @Override
    <S extends UserInfo> S saveAndFlush(S s);
}
