package com.kalayciburak.inventoryservice.advice.exception.car;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.CAR_NOT_AVAILABLE;

public class CarNotAvailableException extends CustomRuntimeException {
    public CarNotAvailableException(String message) {
        super(CAR_NOT_AVAILABLE, message);
    }
}