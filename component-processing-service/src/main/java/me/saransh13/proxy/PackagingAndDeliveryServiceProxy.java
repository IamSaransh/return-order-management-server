package me.saransh13.proxy;

import feign.Headers;
import me.saransh13.model.PackagingAndDeliveryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "packaging-and-delivery-service") // name with which the service is registered on eureka
public interface PackagingAndDeliveryServiceProxy {

   @GetMapping ("/v1/getPackagingAndDeliveryCharge") // mapping for this as general invoking
   @Headers(value = {
           "content-type: application/json",
           "Accept: application/json"
   })
    PackagingAndDeliveryResponse getPackagingAndDeliveryCharge(
             @RequestParam String componentType,
             @RequestParam int count
            );
}