package com.jewelcse.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity(name = "posts")
public class Post implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    private int userId;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
}
