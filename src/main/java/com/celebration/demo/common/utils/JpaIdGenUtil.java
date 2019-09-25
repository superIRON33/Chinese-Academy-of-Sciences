package com.celebration.demo.common.utils;

import com.celebration.demo.service.base.GenIdService;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import java.io.Serializable;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: JPA ID生成策略
 */
public class JpaIdGenUtil extends IdentityGenerator {
    
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        
        return String.valueOf(GenIdService.getId());
    }
}
