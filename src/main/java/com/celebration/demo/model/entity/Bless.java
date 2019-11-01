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
@Table(name = "bless")
public class Bless {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflake_id")
    @GenericGenerator(name = "snowflake_id", strategy = "com.celebration.demo.common.utils.JpaIdGenUtil")
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "create_time")
    private String createTime;

    public Bless(String userId, String content) {
        
        this.userId = userId;
        this.content = content;
        this.image = "";
        this.likes = 0;
    }
}
