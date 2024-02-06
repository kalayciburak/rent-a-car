package com.kalayciburak.inventoryservice.advice.exception.car;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.CAR_NOT_FOUND;

public class CarNotFoundException extends CustomRuntimeException {
    public CarNotFoundException(String message) {
        super(CAR_NOT_FOUND, message);
    }
}