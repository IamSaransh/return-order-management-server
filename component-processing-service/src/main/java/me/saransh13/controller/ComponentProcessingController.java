package me.saransh13.controller;


import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import me.saransh13.service.ProcessDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process/v1")
public class ComponentProcessingController {

    @Autowired
    private ProcessDetailService processDetailService;

    @GetMapping("/processDetail")
    public ResponseEntity<ProcessingResponse> getProcessDetail(@RequestBody ProcessingRequest request){
            return processDetailService.getProcessDetail(request);
    }
}