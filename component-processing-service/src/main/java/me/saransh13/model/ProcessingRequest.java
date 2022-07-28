package me.saransh13.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingRequest {
    private String username;//I'll have it as email
    private String userContactNumber; //@Unique
    private ComponentType componentType;
    private String componentName;
    private int quantity;



}