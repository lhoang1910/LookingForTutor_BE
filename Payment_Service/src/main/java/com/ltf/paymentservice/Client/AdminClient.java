package com.ltf.paymentservice.Client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ADMINSERVICE")
public interface AdminClient {

}
