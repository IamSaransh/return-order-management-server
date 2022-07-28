package me.saransh13.service;

import me.saransh13.model.ProcessingRequest;
import me.saransh13.model.ProcessingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public abstract class ProcessDetailService {

    public ResponseEntity<ProcessingResponse> getProcessDetail(ProcessingRequest request) {
        return null;
    }
}