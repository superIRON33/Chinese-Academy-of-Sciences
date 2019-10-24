package com.celebration.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {
    
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "year")
    private Integer year;

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
    private Integer addressIs;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "telephone_is")
    private Integer telephoneIs;

    @Column(name = "email_add")
    private String emailAdd;

    @Column(name = "email_add_is")
    private Integer emailAddIs;

    @Column(name = "wechat_png")
    private String wechatPNG;

    @Column(name = "slogan")
    private String slogan;
    
    @Column(name = "country")
    private String country;

    public UserInfo(String userId, String image) {
        
        this.id = userId;
        this.name = "";
        this.image = image;
        this.year = 1978;
        this.institute = "";
        this.degree = "";
        this.workspace = "";
        this.workspaceIs = 0;
        this.address = "";
        this.addressIs = 0;
        this.telephone = "";
        this.telephoneIs = 0;
        this.emailAdd = "";
        this.emailAddIs = 0;
        this.wechatPNG = "";
        this.slogan = "";
        this.country = "";
    }
}
