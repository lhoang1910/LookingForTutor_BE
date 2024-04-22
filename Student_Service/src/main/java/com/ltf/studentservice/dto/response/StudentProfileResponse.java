package com.ltf.studentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileResponse {
    private long userId;
    private long studentId;
    private String fullName;
    private String username;
    private String address;
    private String email;
    private String number;
    private int old;
    private int grade;
    private String furtherDescription;
    private String sex;
    private String dateOfBirth;
}
