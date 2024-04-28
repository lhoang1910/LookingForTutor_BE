package com.ltf.classervice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfoResponse {

    private long studentId;
    private long classId;
    private String subject;
    private String grade;
    private double numberOfWeek;
    private String tutorSex;
    private String startTime;
    private String furtherDescription;
    private String address;
    private String studentFullName;
    private int studentOld;
    private String studentSex;
    private int studentGrade;
    private int admissionFee;
    private long tution;
}
