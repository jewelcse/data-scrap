package com.jewelcse.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity(name = "comments")
public class Comment  implements Serializable {

    private static final long serialVersionUID = 7156526077675541623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    private int postId;
    private String name;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String body;
}
