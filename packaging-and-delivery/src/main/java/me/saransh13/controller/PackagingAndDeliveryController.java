package me.saransh13.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.saransh13.config.CostConfig;
import me.saransh13.exception.InvalidComponentTypeException;
import me.saransh13.model.PackagingAndDeliveryResponse;
import me.saransh13.service.PackagingAndDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Slf4j
@RestController
@RequestMapping("/v1")
@Validated
public class PackagingAndDeliveryController {

    private final PackagingAndDeliveryService packagingAndDeliveryService;

    @Autowired
    public PackagingAndDeliveryController(PackagingAndDeliveryService packagingAndDeliveryService) {
        this.packagingAndDeliveryService = packagingAndDeliveryService;
    }

    @GetMapping("/getPackagingAndDeliveryCharge")
    public @ResponseBody  ResponseEntity<PackagingAndDeliveryResponse>  getPackagingAndDeliveryCharge(
            @RequestParam @NotBlank  String componentType,
            @RequestParam   @Min(value = 1, message = "count should be 0 or more") Integer  count)
            throws InvalidComponentTypeException {
        return packagingAndDeliveryService.getPackagingAndDeliveryCharge(componentType, count);
    }

}