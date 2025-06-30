package com.cogip.cogip.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role")
@EqualsAndHashCode(of = {"id", "name"})
@ToString(of =  {"id", "name"})
public class Role {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }
}