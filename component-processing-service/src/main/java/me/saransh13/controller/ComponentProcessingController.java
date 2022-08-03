package me.saransh13.controller;


import lombok.extern.slf4j.Slf4j;
import me.saransh13.exception.InvalidComponentTypeException;
import me.saransh13.model.ComponentType;
import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import me.saransh13.service.AccessoryReplacementService;
import me.saransh13.service.IntegralItemRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static me.saransh13.constants.Constants.INVALID_COMPONENT_MESSAGE;

@Slf4j
@RestController
@RequestMapping("/process/v1")
public class ComponentProcessingController {

//    private ProcessDetailService processDetailService;
    private final IntegralItemRepairService integralItemRepairService;

    private final AccessoryReplacementService accessoryReplacementService;
    @Autowired
    public ComponentProcessingController(IntegralItemRepairService integralItemRepairService, AccessoryReplacementService accessoryReplacementService) {
        this.integralItemRepairService = integralItemRepairService;
        this.accessoryReplacementService = accessoryReplacementService;
    }

    /*
    *
    * GET mapping with a body is not  supported in angularJs
     */
    @PostMapping("/processDetail")
    public ResponseEntity<ProcessingResponse> getProcessDetail(@RequestBody ProcessingRequest request) throws InvalidComponentTypeException {
        ComponentType componentType = request.getComponentType();
        log.info("Received request for a component of type {}",componentType.toString());
        log.debug("The request was of type: {}", request);
        if(componentType.equals(ComponentType.INTEGRAL_ITEM))
        {
            log.info("Integral-Item Service called");
            return integralItemRepairService.getProcessDetail(request);
        }
        if(componentType.equals(ComponentType.ACCESSORY))
        {
            log.info("Accessory Service called");
            return accessoryReplacementService.getProcessDetail(request);
        }
        else{
            throw new InvalidComponentTypeException(INVALID_COMPONENT_MESSAGE);
        }
    }
}