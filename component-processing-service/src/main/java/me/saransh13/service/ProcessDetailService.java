package me.saransh13.service;

import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import org.springframework.http.ResponseEntity;


public abstract class ProcessDetailService {

    abstract ResponseEntity<ProcessingResponse> getProcessDetail(ProcessingRequest request) ;
}