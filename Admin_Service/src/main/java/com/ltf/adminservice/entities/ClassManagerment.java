package com.ltf.adminservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_managerment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassManagerment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long tutorId;

	private long classId;

	private boolean approved;

	private LocalDateTime timeApprove;

	private boolean paid;
	
}
