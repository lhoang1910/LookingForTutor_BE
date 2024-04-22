package com.ltf.tutorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tutor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long userId;

	private String school;

	private String major;

	private String startTime;

	private String endTime;

	private boolean hadExp;

	private String expDescription;

	@OneToOne(mappedBy = "tutor")
	private Image image;


}
