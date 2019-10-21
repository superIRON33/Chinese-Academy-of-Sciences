package com.celebration.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_locate")
public class UserLocate {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "longitude")
    private Float longitude;
    
    @Column(name = "latitude")
    private Float latitude;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "region")
    private String region;
}

