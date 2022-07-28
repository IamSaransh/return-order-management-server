package me.saransh13.proxy;

import feign.Headers;
import me.saransh13.model.PackagingAndDeliveryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "packaging-and-delivery-service" ,
        url="localhost:8080")
public interface PackagingAndDeliveryServiceProxy {

   @GetMapping ("/v1/getPackagingAndDeliveryCharge")
   @Headers(value = {
           "content-type: application/json",
           "Accept: application/json"
   })
    PackagingAndDeliveryResponse getPackagingAndDeliveryCharge(
             @RequestParam String componentType,
             @RequestParam int count
            );
}