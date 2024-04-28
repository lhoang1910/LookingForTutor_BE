	package com.ltf.classervice.entities;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	import java.time.LocalDateTime;

	@Entity
	@Table(name = "class")
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public class Class {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String subject;
		private String grade;
		private double numberOfWeek;
		private double hoursPerSession;
		private String tutorSex;
		private String furtherDescription;
		private int admissionFee;
		private long tution;
		private String address;
		private String startTime;
		private long studentId;
		private boolean isApproved;
		private boolean isPaid;
		private LocalDateTime timeApprove;
		private boolean hadTutor;
	}
