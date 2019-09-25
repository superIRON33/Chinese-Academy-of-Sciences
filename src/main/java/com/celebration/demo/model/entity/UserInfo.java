package com.celebration.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {
    
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflake_id")
    @GenericGenerator(name = "snowflake_id", strategy = "com.celebration.demo.common.utils.JpaIdGenUtil")
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

    @Column(name = "email_is")
    private Integer emailIs;

    @Column(name = "wechat_png")
    private String wechatPNG;

    @Column(name = "slogan")
    private String slogan;

    public UserInfo(String image) {
        
        this.name = "";
        this.image = image;
        this.year = 1978;
        this.institute = "";
        this.degree = "";
        this.workspace = "";
        this.workspaceIs = 0;
        this.address = "";
        this.addressIs = 0;
        this.telephoneIs = 0;
        this.emailIs = 0;
    }
}
