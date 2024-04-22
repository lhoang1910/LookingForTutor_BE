package com.ltf.classervice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.entities.Class;
import com.ltf.classervice.service.ClassService;

@RestController
@RequestMapping("/api/class")
public class ClassController {

	@Autowired
	ClassService service;

	

}
