package com.example.dokotsubu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "MUTTERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mutter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String text;

    public Mutter(String userName, String text) {
        this.userName = userName;
        this.text = text;
    }
}
