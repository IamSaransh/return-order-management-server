package me.saransh13.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackagingAndDeliveryResponse {
    private int deliveryCharge;
    private int packagingCharge;
}
