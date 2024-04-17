package com.ltf.adminservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ltf.adminservice.dto.request.CreateClassRequest;
import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.ClassInfoResponse;

@FeignClient(name = "CLASSSERVICE")
public interface ClassClient {
	@GetMapping("/api/info/all")
	public List<ClassInfoResponse> getAllClassInfo();

	@GetMapping("/api/class/info/{id}")
	public ClassInfoResponse getClassInfoById(@PathVariable long id);

	@PostMapping("/api/add/{id}")
	public AdminResponse get(@RequestBody CreateClassRequest request, @PathVariable long id);

}
