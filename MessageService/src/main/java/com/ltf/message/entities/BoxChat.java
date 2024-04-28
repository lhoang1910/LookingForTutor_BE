package com.ltf.message.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoxChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long tutorId;
    
    private long studentId;
}
