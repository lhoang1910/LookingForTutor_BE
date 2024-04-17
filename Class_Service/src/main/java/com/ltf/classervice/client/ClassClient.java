package com.ltf.classervice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CLASSSERVICE") 
public interface ClassClient {

}
