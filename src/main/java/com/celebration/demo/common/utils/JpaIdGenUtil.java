package com.celebration.demo.common.utils;

import com.celebration.demo.service.base.GenIdService;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import java.io.Serializable;

/**
 * @author: wjy
 * @date: 2019/7/30
 * @description: JPA ID生成策略
 */
public class JpaIdGenUtil extends IdentityGenerator {
    
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        
        return GenIdService.getId();
    }
}
