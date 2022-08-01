package me.saransh13.constants;



public class Constants {
    //todo: to put this in a config and read from properties as a bean
    public static final int DEFAULT_INTEGRAL_PROCESSING_CHARGE = 500;
    public static final int DEFAULT_INTEGRAL_DELIVERY_DURATION = 5;
    public static final int DEFAULT_ACCESSORY_DELIVERY_DURATION = 2;
    public static final int DEFAULT_ACCESSORY_PROCESSING_CHARGE = 300;
    public static final String INVALID_COMPONENT_MESSAGE = "Invalid component type Selected, Choose either from 'Accessory' or 'Integral-Item' ";
    public static final String PAYMENT_FAILED_EXCEPTION_MESSAGE = "PaymentFailedException: Something went wrong while payment";
    public static final String INSUFFICIENT_PAYMENT_LIMIT_MESSAGE = "Insufficient funds/ credit Limit for Transaction";
    public static final String SUCCESS_MESSAGE = "Payment Successful";
}
