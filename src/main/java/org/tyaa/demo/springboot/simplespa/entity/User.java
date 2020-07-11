package org.tyaa.demo.springboot.simplespa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 25)
    private String name;
    @Column(name = "password", nullable = false, length = 16)
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;
}
