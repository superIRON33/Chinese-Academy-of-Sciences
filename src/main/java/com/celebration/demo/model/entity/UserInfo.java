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

    @Column(name = "workspace_is")
    private Integer workspaceIs;

    @Column(name = "address")
    private String address;

    @Column(name = "address_is")
    private String addressIs;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "telephone_is")
    private String telephoneIs;

    @Column(name = "email_add")
    private String emailAdd;

    @Column(name = "email_is")
    private String emailIs;

    @Column(name = "wechat_png")
    private String wechatPNG;

    @Column(name = "slogan")
    private String slogan;

    public UserInfo(String image) {
        
        this.name = "";
        this.image = image;
        this.learnTime = 1949;
        this.institute = "";
        this.degree = "";
        this.workspace = "";
        this.address = "";
    }
}
