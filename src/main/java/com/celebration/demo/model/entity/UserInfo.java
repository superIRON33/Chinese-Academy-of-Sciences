package com.celebration.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "learn_time")
    private Integer learnTime;

    @Column(name = "institute")
    private String institute;

    @Column(name = "degree")
    private String degree;

    @Column(name = "workspace")
    private String workspace;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email_add")
    private String emailAdd;

    @Column(name = "wechat_png")
    private String wechatPNG;

    @Column(name = "slogan")
    private String slogan;
    
    @Column(name = "isPublic")
    private String is_public;

    public UserInfo(String image) {
        
        this.name = "";
        this.image = image;
        this.learnTime = 1949;
        this.institute = "";
        this.degree = "";
        this.workspace = "";
        this.address = "";
        this.is_public = "";
    }
}
