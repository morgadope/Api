package com.morgadope.api.domain;

import lombok.*;
import javax.persistence.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
}
